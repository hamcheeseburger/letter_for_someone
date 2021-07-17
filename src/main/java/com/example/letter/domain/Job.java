package com.example.letter.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="job")
public class Job {
	@Id
	int job_id;
	String title;
	String letter;
	
	@Builder
	public Job(int job_id, String title, String letter) {
		super();
		this.job_id = job_id;
		this.title = title;
		this.letter = letter;
	}
}
