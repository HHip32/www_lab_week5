package vn.edu.iuh.fit.backend.ids;

import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.Skill;

import java.io.Serializable;

public class JobSkillID implements Serializable {
    private Skill skill;
    private Job job;
}
