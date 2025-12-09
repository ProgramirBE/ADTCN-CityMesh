package be.odisee.citymesh.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "drones")
public class Drone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String serienummer;
	private String model;
	private String status;      // ex: "Actief", "Onderhoud"
	private int batterijNiveau; // ex: 85

	public Drone() {}

	public Drone(String serienummer, String model, String status, int batterijNiveau) {
		this.serienummer = serienummer;
		this.model = model;
		this.status = status;
		this.batterijNiveau = batterijNiveau;
	}

	// Getters et Setters (Génère-les avec Alt+Insert ou copie ceux-ci)
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	public String getSerienummer() { return serienummer; }
	public void setSerienummer(String serienummer) { this.serienummer = serienummer; }
	public String getModel() { return model; }
	public void setModel(String model) { this.model = model; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public int getBatterijNiveau() { return batterijNiveau; }
	public void setBatterijNiveau(int batterijNiveau) { this.batterijNiveau = batterijNiveau; }
}