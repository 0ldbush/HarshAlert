package com.alnt.policyengine.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseMasterEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "RULE_SET")
@SQLDelete(sql = "UPDATE RULE_SET SET INT_STATUS = 3 WHERE ID = ?")
public class RuleSet extends BaseMasterEntity {

	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Rule.class, cascade = CascadeType.REFRESH)
	@JoinTable(name = "RULE_SET_RULE_XREF", joinColumns = @JoinColumn(name = "RULE_SET_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "RULE_ID", referencedColumnName = "ID"))
	@Fetch(value = FetchMode.SUBSELECT)
	protected List<Rule> rules = new ArrayList<>();

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

}
