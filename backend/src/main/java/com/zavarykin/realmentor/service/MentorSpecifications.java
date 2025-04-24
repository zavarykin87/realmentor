package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.MentorEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class MentorSpecifications {

    public static Specification<MentorEntity> isConfirm() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isTrue(root.get("confirm"));
        };
    }

    public static Specification<MentorEntity> hasSpecializationsName(List<String> names) {
        return (root, query, criteriaBuilder) -> {
            if (names == null || names.isEmpty()) {
                return criteriaBuilder.conjunction(); // Возвращает "все записи"
            }
            return root.get("specializations").get("name").in(names);
        };
    }

    public static Specification<MentorEntity> hasSkillsName(List<String> names) {
        return (root, query, criteriaBuilder) -> {
            if (names == null || names.isEmpty()) {
                return criteriaBuilder.conjunction(); // Возвращает "все записи"
            }
            return root.get("skills").get("name").in(names);
        };
    }

    public static Specification<MentorEntity> hasExperience(Integer experience) {
        return (root, query, criteriaBuilder) -> {
            if (experience == null) {
                return criteriaBuilder.conjunction(); // Возвращает "все записи"
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), experience);
        };
    }

    public static Specification<MentorEntity> hasCompany(String company) {
        return (root, query, criteriaBuilder) -> {
            if (company == null || company.trim().isEmpty()) {
                return criteriaBuilder.conjunction(); // Возвращает "все записи"
            }
            return criteriaBuilder.equal(root.get("company"), company);
        };
    }

    public static Specification<MentorEntity> hasJobTitle(String jobTitle) {
        return (root, query, criteriaBuilder) -> {
            if (jobTitle == null || jobTitle.trim().isEmpty()) {
                return criteriaBuilder.conjunction(); // Возвращает "все записи"
            }
            return criteriaBuilder.equal(root.get("jobTitle"), jobTitle);
        };
    }
}
