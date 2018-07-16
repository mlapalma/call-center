package model;

import util.RandomGenerator;

/**
 * The type Call.
 * A Call is initiated by a customer identified by callerId
 * This call will have a Status showing its results
 */
public class Call {

	private static final int MINUMUM_CALL_DURATION_MS = 5001;
	private static final int MAXIMUM_CALL_DURATION_MS = 10001;
	private long callerId;
	private CallStatus status;
	private long duration;

	public Call(long callerId) {
		this.callerId = callerId;
		this.duration = (long) RandomGenerator.getRandomNumber(MINUMUM_CALL_DURATION_MS, MAXIMUM_CALL_DURATION_MS);
	}

	public CallStatus getStatus() {
		return status;
	}

	public void setStatus(CallStatus status) {
		this.status = status;
	}

	public long getDuration() {
		return duration;
	}

	public long getCallerId() {
		return callerId;
	}

	@Override
	public String toString() {
		return "Call{" + "callerId=" + callerId + ", status=" + status + ", duration=" + duration + '}';
	}
}
