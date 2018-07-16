package com.mlapalma.model.dto;

import com.mlapalma.model.Call;
import com.mlapalma.model.Customer;
import com.mlapalma.model.Employee;

/**
 * The type Call context dto.
 * This DTO retrieves a call processed context with information of customer, answerer and call itself
 */
public class CallContextDto {
	private Call call;
	private Employee answerer;
	private Customer customer;

	private CallContextDto(Builder builder) {
		call = builder.call;
		answerer = builder.answerer;
		customer = builder.customer;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Call getCall() {
		return call;
	}

	public Employee getAnswerer() {
		return answerer;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String toString() {
		return "CallContextDto{" + "call=" + call + ", answerer=" + answerer + ", customer=" + customer + '}';
	}

	public static final class Builder {
		private Call call;
		private Employee answerer;
		private Customer customer;

		private Builder() {
		}

		public Builder withCall(Call call) {
			this.call = call;
			return this;
		}

		public Builder withAnswerer(Employee answerer) {
			this.answerer = answerer;
			return this;
		}

		public Builder withCustomer(Customer customer) {
			this.customer = customer;
			return this;
		}

		public CallContextDto build() {
			return new CallContextDto(this);
		}
	}
}
