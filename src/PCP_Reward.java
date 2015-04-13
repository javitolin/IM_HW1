import aima.core.probability.mdp.RewardFunction;


public class PCP_Reward implements RewardFunction<PCP_State>{

	@Override
	public double reward(PCP_State state) {
		return (state.isLastPatientSurvived()  ? 1 : 0);
	}

}
