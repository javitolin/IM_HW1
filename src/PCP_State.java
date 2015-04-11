import aima.core.agent.State;


public class PCP_State implements State{
	int stateOfHospital; //0,1,2
	int hour; //9-14
	char diagnose;//f,e,c
	boolean lastPatientSurvived;//yes,no
	
	public PCP_State(int stateOfHospital, int hour, char diagnose,
			boolean lastPatientSurvived) {
		this.stateOfHospital = stateOfHospital;
		this.hour = hour;
		this.diagnose = diagnose;
		this.lastPatientSurvived = lastPatientSurvived;
	}

	public int getStateOfHospital() {
		return stateOfHospital;
	}
	public int getHour() {
		return hour;
	}
	public char getDiagnose() {
		return diagnose;
	}
	public boolean isLastPatientSurvived() {
		return lastPatientSurvived;
	}
	@Override
	public String toString() {
		return "PCP_State [stateOfHospital=" + stateOfHospital + ", hour="
				+ hour + ", diagnose=" + diagnose + ", lastPatientSurvived="
				+ lastPatientSurvived + "]";
	}
}
