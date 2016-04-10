package de.gedoplan.jpa_bv;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 1)
	private String name;

	public Person(String name) {
		this.name = name;
	}

	protected Person() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + this.id + ", name=" + this.name + "]";
	}

	@PrePersist
	public void prePersist() {
		System.out.println("  prePersist");
	}

	@PostPersist
	public void postPersist() {
		System.out.println("  postPersist");
	}

	@PreUpdate
	public void preUpdate() {
		System.out.println("  preUpdate");
	}

	@PostUpdate
	public void postUpdate() {
		System.out.println("  postUpdate");
	}

	@PreRemove
	public void preRemove() {
		System.out.println("  preRemove");
	}

	@PostRemove
	public void postRemove() {
		System.out.println("  postRemove");
	}

	@PostLoad
	public void postLoad() {
		System.out.println("  postLoad");
	}

	@AssertTrue
	public boolean isValid() {
		System.out.println("  isValid");
		return true;
	}
}
