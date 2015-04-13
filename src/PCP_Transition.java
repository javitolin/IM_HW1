import aima.core.probability.mdp.TransitionProbabilityFunction;

public class PCP_Transition implements
		TransitionProbabilityFunction<PCP_State, PCP_Action> {

	@Override
	public double probability(PCP_State from, PCP_State to, PCP_Action act) {
		if (from.getDiagnose() == 'n') {
			//Probability of decease
			if ((to.getHour() != from.getHour())
					|| (from.getStateOfHospital() != to.getStateOfHospital())
					|| from.isLastPatientSurvived() != to
							.isLastPatientSurvived())
				return 0;
			else {
				if (to.getDiagnose() == 'e')
					return 0.1;
				else if (to.getDiagnose() == 'c')
					return 0.1;
				else if (to.getDiagnose() == 'f')
					return 0.8;
				else
					return 0;
			}
		} else {
			//Probability of cure
			double ans = 1;
			if(from.getDiagnose() == 'f' && !to.isLastPatientSurvived())
				return 0;
			if(from.getDiagnose() == 'c' && act == PCP_Action.Hospital && !to.isLastPatientSurvived())
				return 0;
			if (to.isLastPatientSurvived()) {
				if (from.getDiagnose() == 'e')
					if (act == PCP_Action.Hospital)
						ans = ans * 0.25;
					else
						ans = 0;
				else if (from.getDiagnose() == 'f')
					if (act == PCP_Action.Home)
						ans = ans * 0.5;
			}
			if (act == PCP_Action.Hospital) {
				if (to.getStateOfHospital() == 0)
					return 0;
				ans = ans * 0.5;
			}
			boolean correctHour = (to.getHour() - from.getHour() == 1);
			boolean correctStateOfHospital = (from.getStateOfHospital() == 0 || (from
					.getStateOfHospital() - to.getStateOfHospital() == 1));
			if (!(correctHour && correctStateOfHospital))
				return 0;
			return ans;
		}
	}
	/*
	 * @Override public double probability(PCP_State from, PCP_State to,
	 * PCP_Action act) { if(from.getDiagnose() == 's') return 1;
	 * if(from.getHour() == 14) return 0; if(to.getHour() - from.getHour() != 1)
	 * return 0; if(from.getStateOfHospital() == 2 && to.getStateOfHospital() !=
	 * 1) return 0; if(from.getStateOfHospital() == 1 && to.getStateOfHospital()
	 * != 0) return 0; double ans = getPrevProb(from,act);
	 * if(to.getStateOfHospital() == 1 || to.getStateOfHospital() == 2) ans =
	 * ans * 0.5; if(to.getDiagnose() == 'f'){ ans = ans * 0.8; } else
	 * if(to.getDiagnose() == 'c'){ ans = ans * 0.1; } else if(to.getDiagnose()
	 * == 'e'){ ans = ans * 0.1; } return ans; }
	 * 
	 * private double getPrevProb(PCP_State from, PCP_Action act) {
	 * if(act.ordinal() == PCP_Action.Hospital.ordinal()){ if(from.getDiagnose()
	 * == 'e') return 0.25; else return 1; } else{ if(from.getDiagnose() == 'e')
	 * return 0; else if(from.getDiagnose() == 'c') return 0.5; else return 1; }
	 * }
	 */
}
