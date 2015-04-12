import aima.core.probability.mdp.TransitionProbabilityFunction;

public class PCP_Transition implements TransitionProbabilityFunction<PCP_State, PCP_Action>{
	@Override
	public double probability(PCP_State stateSrc, PCP_State stateDst, PCP_Action act) {
		//System.out.println(stateSrc +" "+stateDst +" " + act);
		int homeAct = PCP_Action.Home.ordinal();
		double ans = 1;
		if(stateSrc.getStateOfHospital() == 2 && stateDst.getStateOfHospital() != 1) return 0;
		if(stateSrc.getStateOfHospital() == 1 && stateDst.getStateOfHospital() != 0) return 0;
		if(stateDst.getHour() - stateSrc.getHour() != 1) return 0;
		if(stateSrc.getHour() == 14) return 0;
		if(stateDst.getDiagnose() == 'f'){
			ans*=0.8;
		}
		else if(stateDst.getDiagnose() == 'c'){
			ans*=0.1;
			if(act.ordinal() == homeAct){
				ans*=0.5;
			}
		}
		else if(stateDst.getDiagnose() == 'e'){
			ans*=0.1;
			if(act.ordinal() == homeAct){
				ans*=0;
			}
			else{ //Hospital
				ans*=0.25;
			}
		}
		return ans;
	}

}
