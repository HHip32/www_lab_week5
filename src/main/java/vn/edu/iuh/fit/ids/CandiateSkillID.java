package vn.edu.iuh.fit.ids;

import vn.edu.iuh.fit.models.Candidate;
import vn.edu.iuh.fit.models.Skill;

import java.io.Serializable;

public class CandiateSkillID implements Serializable {
    private Skill skill;
    private Candidate candidate;
}
