package model;

/**
 * The enum Call status.
 * This is the list of Status a call may have
 */
public enum CallStatus {
	ANSWERED_BY_OPERATOR, ANSWERED_BY_SUPERVISOR, ANSWERED_BY_MANAGER, UNNATTENDED_DISPATCHER_OVERFLOW,
	UNNATENDED_UNAVAILABLE_AGENTS, FINISHED
}
