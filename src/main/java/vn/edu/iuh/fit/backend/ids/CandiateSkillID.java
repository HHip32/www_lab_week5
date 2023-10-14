package vn.edu.iuh.fit.backend.ids;

import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Skill;

import java.io.Serializable;

public class CandiateSkillID implements Serializable {
    private Skill skill;
    private Candidate candidate;
}
