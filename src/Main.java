import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import aima.core.probability.mdp.MarkovDecisionProcess;
import aima.core.probability.mdp.search.ValueIteration;

public class Main {
	final static PCP_State initialState = new PCP_State(0, 9, 'n', false);
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		final Set<PCP_State> states = new HashSet<PCP_State>();
		final PCP_Transition transFunc = new PCP_Transition();
		generateStates(states);
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
			public double reward(PCP_State state) {
				return (state.isLastPatientSurvived() ? 1 : 0);
			}
			
			@Override
			public PCP_State getInitialState() {
				return initialState;
			}
			
			/**
			 * Actions function.
			 * If hour is 14, we are done. Return empty list.
			 * Else, check the state of the hospital, if empty, we can add the action.
			 */
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
		Map<PCP_State, Double> mp = vi.valueIteration(mdp, 1);
		
		System.out.println("#####################################");
		Iterator it = mp.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			PCP_State currState = (PCP_State)pair.getKey();
			if(currState.getDiagnose() == 'n' && currState.getHour() == 9 && currState.getStateOfHospital() == 0
					&& !currState.isLastPatientSurvived()) //Start State
					System.out.println(pair.getValue());
	        it.remove();
		}
	}

	private static void generateStates(Set<PCP_State> states){
		int[] hours = {9,10,11,12,13,14};
		int[] stateOfHospital = {0,1,2};
		char[] diagnose = {'n','c','f','e'};
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
