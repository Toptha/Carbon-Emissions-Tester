public class Policy {
    private String policyId;
    private String policyName;
    float emissionReductionGoal;
    public Policy(String policyId, String policyName, float emissionReductionGoal) {
        this.policyId=policyId;
        this.policyName=policyName;
        this.emissionReductionGoal=emissionReductionGoal;
    }
    public void displayDetails() {
        System.out.println("Policy ID: "+policyId);
        System.out.println("Name: "+policyName);
    }
}
