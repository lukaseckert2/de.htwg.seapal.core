package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IRoute;
import de.htwg.seapal.model.ModelDocument;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Route extends ModelDocument implements IRoute {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Long date;
    private List<Double> marks = new LinkedList<>();
	private Double distance;

	public Route() {
        super(UUID.randomUUID().toString());
		marks = new LinkedList<>();
		this.name = "";
		this.date = 0L;
		this.distance = 0D;
	}

	public Route(IRoute r) {
        super(r);
		this.name = r.getName();
		this.date = r.getDate();
		for (Double id : r.getMarks()) {
			marks.add(id);
		}
		this.distance = r.getDistance();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Long getDate() {
		return date;
	}

	@Override
	public void setDate(Long date) {
		this.date = date;
	}

	@Override
	public List<Double> getMarks() {
		return marks;
	}

	@Override
    @JsonIgnore
	public void addMark(UUID mark) {
		throw new Error("do not use function");
	}

	@Override
    @JsonIgnore
    public void deleteMark(UUID mark) {
        throw new Error("do not use function");
    }

	@Override
	public Double getDistance() {
		return distance;
	}

	@Override
	public void setDistance(Double distance) {
		this.distance = distance;
	}

    public void setMarks(final List<Double> marks) {
        this.marks = marks;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
