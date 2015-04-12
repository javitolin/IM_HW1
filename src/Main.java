import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import aima.core.probability.mdp.MarkovDecisionProcess;
import aima.core.probability.mdp.Policy;
import aima.core.probability.mdp.PolicyEvaluation;
import aima.core.probability.mdp.search.PolicyIteration;
import aima.core.probability.mdp.search.ValueIteration;

public class Main {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		final PCP_Reward reward = new PCP_Reward();
		final Set<PCP_State> states = new HashSet<PCP_State>();
		final PCP_Transition transFunc = new PCP_Transition();
		generateStates(states);
		final PCP_State initialState = new PCP_State(0, 9, 'c', false);
		MarkovDecisionProcess<PCP_State, PCP_Action> mdp = new MarkovDecisionProcess<PCP_State, PCP_Action>() {
			@Override
			public double transitionProbability(PCP_State arg0, PCP_State arg1,
					PCP_Action arg2) {
				return transFunc.probability(arg0, arg1, arg2);
			}
			
			@Override
			public Set<PCP_State> states() {
				return states;
			}
			
			@Override
			public double reward(PCP_State arg0) {
				double ans = reward.reward(arg0);
				return ans;
			}
			
			@Override
			public PCP_State getInitialState() {
				return initialState;
			}
			
			@Override
			public Set<PCP_Action> actions(PCP_State state) {
				if(state.getHour() == 14) return new HashSet<PCP_Action>();
				else{
					Set<PCP_Action> ans = new HashSet<PCP_Action>();
					ans.add(PCP_Action.Home);
					if(state.getStateOfHospital() == 0)
						ans.add(PCP_Action.Hospital);
					return ans;
				}
			}
		};
		ValueIteration<PCP_State, PCP_Action> vi = new ValueIteration<PCP_State, PCP_Action>(1);
		Map<PCP_State, Double> mp = vi.valueIteration(mdp, 0.7);
		Iterator it = mp.entrySet().iterator();
		double percentage = 0;
		int count = 0;
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        if(((PCP_State)pair.getKey()).getHour() == 14){
		        percentage += (double)pair.getValue();
		        count++;
	        }
	        it.remove();
		}
		System.out.println("Saved: "+(percentage/count) +" people.");
	}

	private static void generateStates(Set<PCP_State> states){
		int[] hours = {10,11,12,13,14};
		int[] stateOfHospital = {0,1,2};
		char[] diagnose = {'c','f','e'};
		boolean[] lastPatientSurvived = {true,false};
		for(int i = 0; i < hours.length; i++){
			for(int j = 0; j < stateOfHospital.length; j++){
				for(int k = 0; k < diagnose.length; k++){
					for(int l = 0; l < lastPatientSurvived.length; l++){
						states.add(new PCP_State(stateOfHospital[j], hours[i], diagnose[k], lastPatientSurvived[l]));
					}
				}
			}
		}
	}

}
