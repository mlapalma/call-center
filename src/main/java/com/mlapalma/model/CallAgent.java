package com.mlapalma.model;

/**
 * A call agent is someone who can answer a call
 */
public interface CallAgent {

	void answerCall(Call call);

	void finishCall(Call call);

}
