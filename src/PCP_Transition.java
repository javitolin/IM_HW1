import aima.core.probability.mdp.TransitionProbabilityFunction;

public class PCP_Transition implements TransitionProbabilityFunction<PCP_State, PCP_Action>{

	@Override
	public double probability(PCP_State from, PCP_State to, PCP_Action act) {
		if(from.getDiagnose() == 's') return 1;
		if(to.getHour() - from.getHour() != 1) return 0;
		if(from.getStateOfHospital() == 2 && to.getStateOfHospital() != 1) return 0;
		if(from.getStateOfHospital() == 1 && to.getStateOfHospital() != 0) return 0;
		
		double ans = getPrevProb(from,act);
		if(to.getStateOfHospital() == 1 || to.getStateOfHospital() == 2) ans = ans * 0.5;
		if(to.getDiagnose() == 'f'){
			ans = ans * 0.8;
		}
		else if(to.getDiagnose() == 'c'){
			ans = ans * 0.1;
		}
		else if(to.getDiagnose() == 'e'){
			ans = ans * 0.1;
		}
		return ans;
	}

	private double getPrevProb(PCP_State from, PCP_Action act) {
		/*if(from.isLastPatientSurvived())
			return 1;
			*/
		if(act.ordinal() == PCP_Action.Hospital.ordinal()){
			if(from.getDiagnose() == 'e')
				return 0.25;
			else
				return 1;
		}
		else{
			if(from.getDiagnose() == 'e')
				return 0;
			else if(from.getDiagnose() == 'c')
				return 0.5;
			else 
				return 1;
		}
	}
}
