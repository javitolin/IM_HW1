import aima.core.probability.mdp.RewardFunction;


public class PCP_Reward implements RewardFunction<PCP_State>{

	@Override
	public double reward(PCP_State state) {
		//if(state.isLastPatientSurvived() || state.getDiagnose() == 'f') I don't think this is right.. 
		if(state.isLastPatientSurvived())
			return 1;
		return 0;
	}

}
