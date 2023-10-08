package vn.edu.iuh.fit.ids;

import vn.edu.iuh.fit.models.Job;
import vn.edu.iuh.fit.models.Skill;

import java.io.Serializable;

public class JobSkillID implements Serializable {
    private Skill skill;
    private Job job;
}
