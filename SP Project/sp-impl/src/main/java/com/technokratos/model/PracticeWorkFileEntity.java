package com.technokratos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "practice_work_file")
public class PracticeWorkFileEntity extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "practice_work_id")
    private PracticeWorkEntity practiceWork;

    @OneToOne
    @JoinColumn(name = "file_id")
    private FileEntity file;

}

