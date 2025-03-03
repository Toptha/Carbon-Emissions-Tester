package energyEmissions;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class threader {
    private ExecutorService executor;
    private List<String> energyTypes;
    private List<Double> emissionFactors;
    private final ReentrantLock lock=new ReentrantLock();
    private Map<String, Thread> backgroundTasks=new ConcurrentHashMap<>();
    
    public threader() {
        executor=Executors.newFixedThreadPool(10);
        
        energyTypes=Arrays.asList(
            "Coal Electricity", "Natural Gas Electricity", "Renewable Energy", "Diesel", "Gasoline"
        );
        
        emissionFactors=Arrays.asList(0.94, 0.41, 0.0, 2.68, 2.31);

        BackgroundMonitor monitor=new BackgroundMonitor();
        Thread monitorThread=new Thread(monitor);
        monitorThread.setPriority(Thread.MIN_PRIORITY);
        monitorThread.setName("MonitorThread");
        monitorThread.start();
        backgroundTasks.put("Monitor", monitorThread);
    }

    private class BackgroundMonitor extends Thread {
        private volatile boolean running=true;

        @Override
        public void run() {
            while (running) {
                try {
                    System.out.println("[Monitor] System check - Active calculations: "+
                        backgroundTasks.size());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        public void stopMonitoring() {
            running=false;
        }
    }

    private class EmissionLogger implements Runnable {
        private final String energyType;
        private final double emission;

        public EmissionLogger(String energyType, double emission) {
            this.energyType=energyType;
            this.emission=emission;
        }

        @Override
        public void run() {
            synchronized(System.out) {
                System.out.println("[Logger] Logging emission for "+energyType+
                    ": "+emission+" kg CO₂");
            }
        }
    }

    private class EmissionCalculator implements Callable<Double> {
        private final double energyConsumed;
        private final double emissionFactor;
        private final String energyType;

        public EmissionCalculator(String energyType, double energyConsumed, 
            double emissionFactor) {
            this.energyType=energyType;
            this.energyConsumed=energyConsumed;
            this.emissionFactor=emissionFactor;
        }

        @Override
        public Double call() {
            Thread calculatorThread=Thread.currentThread();
            calculatorThread.setPriority(Thread.MAX_PRIORITY); 
            lock.lock();
            try {
                double result=energyConsumed * emissionFactor;
                Thread loggerThread=new Thread(new EmissionLogger(energyType, result));
                loggerThread.setPriority(Thread.NORM_PRIORITY);
                loggerThread.start();
                backgroundTasks.put("Logger-"+energyType, loggerThread);
                return result;
            } finally {
                lock.unlock();
            }
        }
    }

    public double calculateTotalEmissions(List<Integer> selectedSources, 
        double energyConsumed) {
        List<Future<Double>> emissionResults=new ArrayList<>();

        for (int source:selectedSources) {
            if (source>=1&&source<=energyTypes.size()) {
                String energyType=energyTypes.get(source - 1);
                Callable<Double> task=new EmissionCalculator(
                    energyType,
                    energyConsumed,
                    emissionFactors.get(source - 1)
                );
                emissionResults.add(executor.submit(task));
            }
        }

        double totalEmissions=0;
        for (Future<Double> result:emissionResults) {
            try {
                totalEmissions += result.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return totalEmissions;
    }

    public void displayThreadStatus() {
        System.out.println("\nActive Threads Status:");
        backgroundTasks.forEach((name, thread) -> {
            System.out.println("Thread: "+name+
                " | Priority: "+thread.getPriority()+
                " | State: "+thread.getState());
        });
    }

    public void shutdown() {
        backgroundTasks.forEach((name, thread) -> {
            if (thread instanceof BackgroundMonitor) {
                ((BackgroundMonitor) thread).stopMonitoring();
            }
            thread.interrupt();
        });
        executor.shutdown();
        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    public void displayEnergyOptions() {
        System.out.println("Energy sources: ");
        for (int i=0; i < energyTypes.size(); i++) {
            System.out.println((i+1)+". "+energyTypes.get(i));
        }
    }
}