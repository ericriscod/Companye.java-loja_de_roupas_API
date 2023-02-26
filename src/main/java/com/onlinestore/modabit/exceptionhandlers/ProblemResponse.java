package com.onlinestore.modabit.exceptionhandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemResponse {
	private int code;
	private String Messenger;
}
