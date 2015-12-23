package controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import modelo.entidades.Reservatorio;

@ManagedBean(eager = true)
@ApplicationScoped
public class PrincipalBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1867153195436392571L;
	
	public PrincipalBean() {
		System.out.println("*********O Managed Bean PrincipalBean foi inicializado *******");
	}
	
	@PostConstruct
	public void init() {
		this.criaModeloDashboard();
		this.criaModeloGrafico();
	}
	
	/*
	 * 
	 * DASHBOARD da parte Principal
	 * 
	 */
	private DashboardModel dashboardModelo;	 
	 

	private void criaModeloDashboard() {
		 this.dashboardModelo = new DefaultDashboardModel();
		 
		 DashboardColumn coluna1 = new DefaultDashboardColumn();
	     DashboardColumn coluna2 = new DefaultDashboardColumn();
	     DashboardColumn coluna3 = new DefaultDashboardColumn();
	     		 
	     coluna1.addWidget("reservatorio");
	     coluna2.addWidget("estadobomba");
	     coluna3.addWidget("estadovalula");
	     coluna3.addWidget("alteravaloraguareservatorio");
	     
	     this.dashboardModelo.addColumn(coluna1);
	     this.dashboardModelo.addColumn(coluna2);
	     this.dashboardModelo.addColumn(coluna3);
	 }
	 
	 public void handleReorder(DashboardReorderEvent event) {
	        FacesMessage message = new FacesMessage();
	        message.setSeverity(FacesMessage.SEVERITY_INFO);
	        message.setSummary("Reordered: " + event.getWidgetId());
	        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
	         
	        addMessage(message);
	    }
	     
	    public void handleClose(CloseEvent event) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
	         
	        addMessage(message);
	    }
	     
	    public void handleToggle(ToggleEvent event) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
	         
	        addMessage(message);
	    }
	     
	    private void addMessage(FacesMessage message) {
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	    
	    /*
		 * GETTERS AND SETTERS para reservatório
		 */
	    public DashboardModel getDashboardModelo() {
			return dashboardModelo;
		}
	    
	    
	/*
	 * 
	 * FIM DASHBOARD da parte Principal
	 * 
	 */
	
	/*
	 * RESERVATORIO
	 * 
	 * Essa parte trata dos dados relacionados ao reservatório de água
	 * 
	 */
	private BarChartModel barra;	
	private ChartSeries reservatorioGrafico = new ChartSeries("Água");
	private Reservatorio reservatorioFisico = new Reservatorio();
	private int volumeDeAgua;
		
	
	

	private void criaModeloGrafico() {
		this.criaModeloGraficoEmBarra();
	}
	
	private void criaModeloGraficoEmBarra() {
		barra = this.modeloInicialEmBarra();
        
        barra.setLegendPosition("ne");
         
        Axis xAxis = barra.getAxis(AxisType.X);
        xAxis.setLabel("Reservatório");
         
        Axis yAxis = barra.getAxis(AxisType.Y);
        yAxis.setLabel("Volume em %");
        yAxis.setMin(0);
        yAxis.setMax(100);
	}
	
	private BarChartModel modeloInicialEmBarra() {
		BarChartModel modelo = new BarChartModel();
		
		modelo.addSeries(this.reservatorioGrafico);
		
		this.atualizaVolumeAguaNoGrafico();
		
		return modelo;
	}
	
	public void atualizaVolumeAguaNoGrafico() {
		this.reservatorioGrafico.set(
				"Agora", this.reservatorioFisico.calculaNivelDeAguaReservatorio());		
	}
	
	public void atualizaVolumeDeAgua() {
		this.setVolumeDeAgua(this.reservatorioFisico.calculaNivelDeAguaReservatorio());
	}
	
	/*
	 * Exibe informações de quantidade de água ao clicar no gráfico
	 */	
	public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(
        		FacesMessage.SEVERITY_INFO,
        		"Detalhes Reservatório",
        		"O reservatório esta com " +
        		this.reservatorioFisico.calculaNivelDeAguaReservatorio() +
        		"%" + " da capacidade total.");
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	/*
	 * GETTERS AND SETTERS para reservatório de água
	 */
	public BarChartModel getBarra() {
		return barra;
	}
	
	public int getVolumeDeAgua() {
		return volumeDeAgua;
	}

	public void setVolumeDeAgua(int volumeDeAgua) {
		this.volumeDeAgua = volumeDeAgua;
	}
	
	/*
	 * FIM RESERVATORIO
	 * 
	 */
}
