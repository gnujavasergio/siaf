package view;

import bibliothek.extension.gui.dock.theme.EclipseTheme;
import bibliothek.gui.DockFrontend;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.DefaultDockable;
import bibliothek.gui.dock.FlapDockStation;
import bibliothek.gui.dock.ScreenDockStation;
import bibliothek.gui.dock.SplitDockStation;
import bibliothek.gui.dock.station.split.SplitDockGrid;
import bibliothek.gui.dock.station.split.SplitDockProperty;
import bibliothek.gui.dock.support.lookandfeel.ComponentCollector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jdbc.Conexion;
import model.dao.CommonDAO;
import model.domain.Configuracion;
import model.domain.Entidad;
import model.domain.Unidad;
import model.domain.Usuario;
import org.jc.swing.panel.PanelGlassTinte;
import utils.dock.Minimizer;
import view.activos.ActivoFijoForm;
import view.activos.ActivoFijoView;
import view.activos.BajaView;
import view.activos.RevaluoView;
import view.administracion.AuxiliarView;
import view.administracion.GrupoView;
import view.configuracion.EntidadForm;
import view.configuracion.EntidadView;
import view.administracion.OficinaView;
import view.administracion.ResponsableView;
import view.configuracion.UnidadView;
import view.common.CommonDialog;
import view.configuracion.ConfiguracionView;
import view.configuracion.UnidadForm;
import view.report.SelectReportForm;
import view.seguridad.RespaldarForm;
import view.seguridad.RestaurarForm;
import view.seguridad.UsuarioView;
import view.viewMain.*;

/**
 * ViewMain
 *
 * @author Ochoa Martinez Sergio Antonio - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public final class ViewMain extends JFrame implements ComponentCollector {

    public PanelGlassTinte glassTinte;
    public static ViewMain viewMain;
    public static CommonDAO DAO;
    private DockFrontend frontend;
    private ScreenDockStation screen;
    private SplitDockStation station;
    private FlapDockStation north;
    private FlapDockStation south;
    private FlapDockStation east;
    private FlapDockStation west;
    private boolean onThemeUpdate = false;
    private RespaldarForm respaldarForm;
    private RestaurarForm restaurarForm;
    private PanelInicio panelInicio;
    private DefaultDockable dockableInicio;
    private ResponsableView responsableView;
    private DefaultDockable dockableResponsable;
    private OficinaView oficinaView;
    private DefaultDockable dockableOficina;
    private EntidadView entidadView;
    private DefaultDockable dockableEntidad;
    private UnidadView unidadView;
    private DefaultDockable dockableUnidad;
    private UsuarioView usuarioView;
    private DefaultDockable dockableUsuario;
    private GrupoView grupoView;
    private DefaultDockable dockableGrupo;
    private AuxiliarView auxiliarView;
    private DefaultDockable dockableAuxiliar;
    private ActivoFijoView activoFijoView;
    private DefaultDockable dockableActivoFijo;
    private RevaluoView revaluoView;
    private DefaultDockable dockableRevaluo;
    private BajaView bajaView;
    private DefaultDockable dockableBaja;
    private SelectReportForm selectReportForm;
    private DefaultDockable dockableSelectReport;
    private ConfiguracionView configuracionView;
    private DefaultDockable dockableConfiguracion;
    private CtrlViewMain ctrlViewMain;
    private Entidad entidad = new Entidad();
    private EntidadForm entidadForm;
    private CommonDialog commonDialog;
    private Unidad unidad = new Unidad();
    private UnidadForm unidadForm;
    private Configuracion configuracion;
    private boolean sw = false;
    private Usuario usuario;
    private Conexion conexion;

    public ViewMain(CommonDAO dao, Usuario usuario, Conexion conexion) {
        Image icon = new ImageIcon(getClass().getResource("/resources/images/logo-presto.jpg")).getImage();
        setIconImage(icon);
        DAO = dao;
        this.conexion = conexion;
        viewMain = this;
        this.usuario = usuario;
        this.ctrlViewMain = new CtrlViewMain();
        initComponents();
        configuracion = new Configuracion();
        configuracion.setFechaUFV(Calendar.getInstance().getTime());
        configuracion = (Configuracion) dao.getObject(configuracion);
//        if (configuracion.getUFV() != null) {
//            lUFV.setText("<html><b>UFV: </b>" + configuracion.getUFV() + "</html>");
//        } else {
//            JOptionPane.showMessageDialog(this, "Las UFV no estan actualizadas, contactese con su proveedor", "Mensaje", JOptionPane.ERROR_MESSAGE);
//            configuracion.setUFV("0.0");
//            lUFV.setText("<html><b>UFV: </b>" + configuracion.getUFV() + "</html>");
//        }
        configuracion.setUFV("0.0");
            lUFV.setText("<html><b>UFV: </b>" + configuracion.getUFV() + "</html>");
        initAplication();
        initDocking();
        initGlassPane();
        initConfiguracion();
        lUFV.setText("<html><b>UFV: </b>" + configuracion.getUFV() + "</html>");
        lUsuario.setText("<html><b>Usuario: </b>" + usuario.getNombres() + "</html>");

    }

    public ViewMain get(){
        return viewMain;
    }
    
    public void initConfiguracion() {
        boolean enabled = true;
        if (usuario.getTipo().equals("Operador")) {
            enabled = false;
        }
        entidadView.getbNew().setEnabled(enabled);
        entidadView.getbUpdate().setEnabled(enabled);
        entidadView.getbDelete().setEnabled(enabled);
        unidadView.getbNew().setEnabled(enabled);
        unidadView.getbUpdate().setEnabled(enabled);
        unidadView.getbDelete().setEnabled(enabled);
        ActivoFijoForm.activoFijoForm.getbApiEstado().setEnabled(enabled);
    }

    public void initAplication() {
        List<Entidad> listEntidades = DAO.getObjects(new Entidad());
        boolean estadoEntidad = true;
        for (Entidad e : listEntidades) {
            if (e.isEstado()) {
                estadoEntidad = false;
            }
        }
        if (estadoEntidad) {
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    commonDialog = new CommonDialog(ViewMain.viewMain, true);
                    entidadForm = new EntidadForm();
                    entidadForm.setObject(new Entidad());
                    entidadForm.getbSelect().setVisible(true);
                    entidadForm.getbGuardar().setVisible(false);
                    entidadForm.getbCancelar().setVisible(false);
                    entidadForm.getCbEntidad().setVisible(true);
                    entidadForm.getCbNuevo().setVisible(true);
                    entidadForm.getTfNombre().setEditable(false);
                    entidadForm.getTfSigla().setEditable(false);
                    entidadForm.getTfInstitucion().setEditable(false);
                    entidadForm.initComboBoxEntidad(ViewMain.DAO.getObjects(new Entidad()));
                    entidadForm.getHeaderEntidad().setDescription("Esta Entidad sera Seleccionada para empezar el Programa");
                    commonDialog.add(entidadForm);
                    commonDialog.visibleDialog("Nueva Entidad");
                    entidad = (Entidad) ViewMain.DAO.getObject(new Entidad());
                    lEntidad.setText("<html><b>Entidad: </b>" + entidad.getIdEntidad() + " " + entidad.getNombre() + "</b></html>");
                    lUnidad.setText("");
                    timer.cancel();
                }
            }, 0, 1000);
        }
        if (!estadoEntidad) {
            entidad = (Entidad) ViewMain.DAO.getObject(new Entidad());
            lEntidad.setText("<html><b>Entidad: </b>" + entidad.getIdEntidad() + " " + entidad.getNombre() + "</b></html>");
            lUnidad.setText("");
        }
        Unidad u = new Unidad();
        u.setEntidad(entidad);
        List<Unidad> listUnidades = DAO.getObjects(u);
        boolean estadoUnidad = false;
        for (Unidad un : listUnidades) {
            if (un.isEstado()) {
                estadoUnidad = true;
            }
        }
        if (sw) {
            if (!estadoUnidad && listUnidades.isEmpty()) {
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        commonDialog = new CommonDialog(ViewMain.viewMain, true);
                        unidadForm = new UnidadForm();
                        unidadForm.setObject(new Unidad());
                        unidadForm.getbSelect().setVisible(true);
                        unidadForm.getbGuardar().setVisible(false);
                        unidadForm.getbCancelar().setVisible(false);
                        unidadForm.getHeaderUnidad().setDescription("Esta Unidad sera deleccionada para empezar el Programa.");
                        commonDialog.setTitle("Nueva Unidad");
                        commonDialog.add(unidadForm);
                        commonDialog.visibleDialog("Nueva Unidad");
                        unidad.setEntidad(entidad);
                        unidad = (Unidad) DAO.getObject(unidad);
                        lUnidad.setText("<html><b>Unidad: </b>" + unidad.getIdUnidad() + "  " + unidad.getDescripcion() + "</b></html>");
                        timer.cancel();
                    }
                }, 0, 1000);
            }
        }
        if (estadoUnidad && !listUnidades.isEmpty()) {
            unidad.setEntidad(entidad);
            unidad = (Unidad) DAO.getObject(unidad);
            lUnidad.setText("<html><b>Unidad: </b>" + unidad.getIdUnidad() + "  " + unidad.getDescripcion() + "</b></html>");
        } else if (!listUnidades.isEmpty()) {
            if (unidad.getIdUnidad() != 0) {
                lUnidad.setText("<html><b>Unidad: </b>" + unidad.getIdUnidad() + "  " + unidad.getDescripcion() + "</b></html>");
                UnidadView.unidadView.getUnidadTable().reload();
                frontend.hide(dockableUnidad);
                frontend.show(dockableUnidad);
                JOptionPane.showMessageDialog(ViewMain.viewMain, "Tiene que seleccionar una unidad de la siguiente lista o Crear una nueva unidad");
            }
        }
    }

    private void initGlassPane() {
        glassTinte = new PanelGlassTinte(this, 0.6f);
        glassTinte.setTinte(Color.BLACK);
        this.setGlassPane(glassTinte);
    }

    /**
     * Iniciamos el Docking
     */
    private void initDocking() {

        frontend = new DockFrontend(this);
        north = new FlapDockStation();
        south = new FlapDockStation();
        east = new FlapDockStation();
        west = new FlapDockStation();
        screen = new ScreenDockStation(this);
        station = new SplitDockStation();

        //metodo para que aparezca el icono x
        frontend.setDefaultHideable(true);

        Minimizer minimizer = new Minimizer(this, frontend.getController());

        minimizer.addAreaNormalized(station);
        minimizer.addAreaNormalized(screen);
        minimizer.setDefaultStation(station);

        Container content = this.getContentPane();
        Container center = new Container();
        content.add(center, BorderLayout.CENTER);
        center.setLayout(new BorderLayout());
        center.add(station, BorderLayout.CENTER);
//        center.add( south.getComponent(), BorderLayout.SOUTH );
//        center.add( north.getComponent(), BorderLayout.NORTH );
        center.add(east.getComponent(), BorderLayout.EAST);
        center.add(west.getComponent(), BorderLayout.WEST);

//        minimizer.addAreaMinimized( north, SplitDockProperty.NORTH );
//        minimizer.addAreaMinimized( south, SplitDockProperty.SOUTH );
        minimizer.addAreaMinimized(east, SplitDockProperty.EAST);
        minimizer.addAreaMinimized(west, SplitDockProperty.WEST);

//        frontend.addRoot( "north" , north);
//        frontend.addRoot( "south", south );
        frontend.addRoot("east", east);
        frontend.addRoot("west", west);
        frontend.addRoot("root", station);
        frontend.addRoot("screen", screen);

        initContenedor();

        SplitDockGrid grid = new SplitDockGrid();
        /**
         * x-columna,y-fila,width-ancho,height-alto
         */
        grid.addDockable(0, 0, 3, 3, dockableInicio);
        grid.addDockable(0, 0, 3, 3, dockableOficina);
        grid.addDockable(0, 0, 3, 3, dockableResponsable);
        grid.addDockable(0, 0, 3, 3, dockableEntidad);
        grid.addDockable(0, 0, 3, 3, dockableUnidad);
        grid.addDockable(0, 0, 3, 3, dockableUsuario);
        grid.addDockable(0, 0, 3, 3, dockableGrupo);
        grid.addDockable(0, 0, 3, 3, dockableAuxiliar);
        grid.addDockable(0, 0, 3, 3, dockableActivoFijo);
        grid.addDockable(0, 0, 3, 3, dockableRevaluo);
        grid.addDockable(0, 0, 3, 3, dockableBaja);
        grid.addDockable(0, 0, 3, 3, dockableConfiguracion);
        grid.addDockable(0, 0, 3, 3, dockableSelectReport);
        station.dropTree(grid.toTree());
        //para que se oculte el docking
        frontend.hide(dockableOficina);
        frontend.hide(dockableResponsable);
        frontend.hide(dockableEntidad);
        frontend.hide(dockableUnidad);
        frontend.hide(dockableUsuario);
        frontend.hide(dockableGrupo);
        frontend.hide(dockableAuxiliar);
        frontend.hide(dockableActivoFijo);
        frontend.hide(dockableRevaluo);
        frontend.hide(dockableBaja);
        frontend.hide(dockableConfiguracion);
        frontend.hide(dockableSelectReport);
        //para  que se añada al frontend si no se coloca esto las ventanas salen como flotantes
        frontend.setDefaultStation(station);
        //para colocar el tema a los docking
        frontend.getController().setTheme(new EclipseTheme());
    }

    @Override
    public Collection<Component> listComponents() {
        List<Component> list = new ArrayList<>();
        list.add(this);
        for (Dockable d : frontend.getController().getRegister().listDockables()) {
            list.add(d.getComponent());
        }
        return list;
    }

    private void initContenedor() {
        //Inicio
        panelInicio = new PanelInicio();
        dockableInicio = new DefaultDockable(panelInicio, "Inicio");
        frontend.addDockable("Inicio", dockableInicio);
        //oficina
        oficinaView = new OficinaView();
        dockableOficina = new DefaultDockable(oficinaView, "Oficina");
        frontend.addDockable("Oficina", dockableOficina);
        //responsable
        responsableView = new ResponsableView();
        dockableResponsable = new DefaultDockable(responsableView, "Responsable");
        frontend.addDockable("Responsable", dockableResponsable);
        //Entidad
        entidadView = new EntidadView();
        dockableEntidad = new DefaultDockable(entidadView, "Entidad");
        frontend.addDockable("Entidad", dockableEntidad);
        //responsable
        unidadView = new UnidadView();
        dockableUnidad = new DefaultDockable(unidadView, "Unidad");
        frontend.addDockable("Unidad", dockableUnidad);
        //usuario
        usuarioView = new UsuarioView();
        dockableUsuario = new DefaultDockable(usuarioView, "Usuario");
        frontend.addDockable("Usuario", dockableUsuario);
        //grupo
        grupoView = new GrupoView();
        dockableGrupo = new DefaultDockable(grupoView, "Grupo");
        frontend.addDockable("Grupo", dockableGrupo);
        //Auxiliar
        auxiliarView = new AuxiliarView();
        dockableAuxiliar = new DefaultDockable(auxiliarView, "Auxiliar");
        frontend.addDockable("Auxiliar", dockableAuxiliar);
        //ActivoFijo
        activoFijoView = new ActivoFijoView();
        dockableActivoFijo = new DefaultDockable(activoFijoView, "Activo Fijo");
        frontend.addDockable("Activo Fijo", dockableActivoFijo);
        //Revaluo
        revaluoView = new RevaluoView();
        dockableRevaluo = new DefaultDockable(revaluoView, "Revaluo");
        frontend.addDockable("Revaluo", dockableRevaluo);
        //Baja
        bajaView = new BajaView();
        dockableBaja = new DefaultDockable(bajaView, "Baja");
        frontend.addDockable("Baja", dockableBaja);
        //Configuracion
        configuracionView = new ConfiguracionView();
        dockableConfiguracion = new DefaultDockable(configuracionView, "UFV");
        frontend.addDockable("UFV", dockableConfiguracion);
        //ReportForm
        selectReportForm = new SelectReportForm();
        dockableSelectReport = new DefaultDockable(selectReportForm, "Reporte");
        frontend.addDockable("Reporte", dockableSelectReport);
        //RespaldarForm
        respaldarForm = new RespaldarForm();
        //RestaurarForm
        restaurarForm = new RestaurarForm();
        
//        viewHierarchy = new DefaultDockable();
//        viewHierarchy.setTitleText("hierarchy");
//        frontend.addDockable("hierarchy", viewHierarchy);
//        Container containerHierarchy = viewHierarchy.getContentPane();
//        containerHierarchy.add(hierarchy);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusBar = new org.jdesktop.swingx.JXStatusBar();
        lUsuario = new javax.swing.JLabel();
        clockDigital1 = new org.jc.util.ClockDigital();
        lUFV = new javax.swing.JLabel();
        lEntidad = new javax.swing.JLabel();
        lUnidad = new javax.swing.JLabel();
        jTaskPane1 = new com.l2fprod.common.swing.JTaskPane();
        tpgActivosFijos = new com.l2fprod.common.swing.JTaskPaneGroup();
        bActivoFijo = new javax.swing.JButton();
        bReport = new javax.swing.JButton();
        tpgAdministracion = new com.l2fprod.common.swing.JTaskPaneGroup();
        bOficina = new javax.swing.JButton();
        bResponsable = new javax.swing.JButton();
        bGrupo = new javax.swing.JButton();
        bAuxiliar = new javax.swing.JButton();
        tpgConfiguracion = new com.l2fprod.common.swing.JTaskPaneGroup();
        bEntidad = new javax.swing.JButton();
        bUnidad = new javax.swing.JButton();
        tpgSistema = new com.l2fprod.common.swing.JTaskPaneGroup();
        bUsuario = new javax.swing.JButton();
        bConfiguracion = new javax.swing.JButton();
        bRespaldar = new javax.swing.JButton();
        bRestaurar = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        mArchivo = new javax.swing.JMenu();
        miSalir = new javax.swing.JMenuItem();
        mAdministracion = new javax.swing.JMenu();
        miOficina = new javax.swing.JMenuItem();
        miResponsable = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        miGrupo = new javax.swing.JMenuItem();
        miAuxiliar = new javax.swing.JMenuItem();
        mConfiguracion = new javax.swing.JMenu();
        miEntidad = new javax.swing.JMenuItem();
        miUnidad = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        miUsuario = new javax.swing.JMenuItem();
        miConfiguracion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miRespaldar = new javax.swing.JMenuItem();
        miRestaurar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lUsuario.setText("Usuario:");
        statusBar.add(lUsuario);
        statusBar.add(clockDigital1);

        lUFV.setText("UFV:");
        lUFV.setToolTipText("");
        statusBar.add(lUFV);
        statusBar.add(lEntidad);
        statusBar.add(lUnidad);

        getContentPane().add(statusBar, java.awt.BorderLayout.PAGE_END);

        tpgActivosFijos.setTitle("Activos Fijos");
        tpgActivosFijos.setToolTipText("");

        bActivoFijo.setText("Activos Fijos");
        bActivoFijo.addActionListener(ctrlViewMain);
        bActivoFijo.setFocusable(false);
        bActivoFijo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bActivoFijo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgActivosFijos.getContentPane().add(bActivoFijo);

        bReport.setText("Generar Reportes");
        bReport.setFocusable(false);
        bReport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bReport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bReport.addActionListener(ctrlViewMain);
        tpgActivosFijos.getContentPane().add(bReport);

        jTaskPane1.add(tpgActivosFijos);

        tpgAdministracion.setTitle("Administración");

        bOficina.setText("Oficina");
        bOficina.addActionListener(ctrlViewMain);
        bOficina.setFocusable(false);
        bOficina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bOficina.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgAdministracion.getContentPane().add(bOficina);

        bResponsable.setText("Responsable");
        bResponsable.addActionListener(ctrlViewMain);
        bResponsable.setFocusable(false);
        bResponsable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bResponsable.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgAdministracion.getContentPane().add(bResponsable);

        bGrupo.setText("Grupo");
        bGrupo.addActionListener(ctrlViewMain);
        bGrupo.setFocusable(false);
        bGrupo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bGrupo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgAdministracion.getContentPane().add(bGrupo);

        bAuxiliar.setText("Auxiliar");
        bAuxiliar.addActionListener(ctrlViewMain);
        bAuxiliar.setFocusable(false);
        bAuxiliar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bAuxiliar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgAdministracion.getContentPane().add(bAuxiliar);

        jTaskPane1.add(tpgAdministracion);

        tpgConfiguracion.setTitle("Configuración");

        bEntidad.setText("Entidades");
        bEntidad.setFocusable(false);
        bEntidad.addActionListener(ctrlViewMain);
        bEntidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bEntidad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgConfiguracion.getContentPane().add(bEntidad);

        bUnidad.setText("Unidades Administrativas");
        bUnidad.setFocusable(false);
        bUnidad.addActionListener(ctrlViewMain);
        bUnidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bUnidad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgConfiguracion.getContentPane().add(bUnidad);

        jTaskPane1.add(tpgConfiguracion);

        tpgSistema.setTitle("Sistema");

        bUsuario.setText("Usuarios");
        bUsuario.setFocusable(false);
        bUsuario.addActionListener(ctrlViewMain);
        bUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgSistema.getContentPane().add(bUsuario);

        bConfiguracion.setText("Listar UFVs");
        bConfiguracion.setFocusable(false);
        bConfiguracion.addActionListener(ctrlViewMain);
        bConfiguracion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bConfiguracion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgSistema.getContentPane().add(bConfiguracion);

        bRespaldar.setText("Respaldar BD");
        bRespaldar.setFocusable(false);
        bRespaldar.addActionListener(ctrlViewMain);
        bRespaldar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bRespaldar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgSistema.getContentPane().add(bRespaldar);

        bRestaurar.setText("Restaurar BD");
        bRestaurar.setFocusable(false);
        bRestaurar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bRestaurar.addActionListener(ctrlViewMain);
        bRestaurar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tpgSistema.getContentPane().add(bRestaurar);

        jTaskPane1.add(tpgSistema);

        getContentPane().add(jTaskPane1, java.awt.BorderLayout.LINE_START);

        mArchivo.setText("Archivo");

        miSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        miSalir.setText("Salir");
        miSalir.addActionListener(ctrlViewMain);
        mArchivo.add(miSalir);

        menuBar.add(mArchivo);

        mAdministracion.setText("Administración");

        miOficina.setText("Oficina");
        miOficina.addActionListener(ctrlViewMain);
        mAdministracion.add(miOficina);

        miResponsable.setText("Responsable");
        miResponsable.addActionListener(ctrlViewMain);
        mAdministracion.add(miResponsable);
        mAdministracion.add(separador);

        miGrupo.setText("Grupo");
        miGrupo.addActionListener(ctrlViewMain);
        mAdministracion.add(miGrupo);

        miAuxiliar.setText("Auxiliar");
        miAuxiliar.addActionListener(ctrlViewMain);
        mAdministracion.add(miAuxiliar);

        menuBar.add(mAdministracion);

        mConfiguracion.setText("Configuración");

        miEntidad.setText("Entidades");
        miEntidad.addActionListener(ctrlViewMain);
        mConfiguracion.add(miEntidad);

        miUnidad.setText("Unidades Administrativas");
        miUnidad.addActionListener(ctrlViewMain);
        mConfiguracion.add(miUnidad);

        menuBar.add(mConfiguracion);

        jMenu1.setText("Sistema");

        miUsuario.setText("Usuarios");
        miUsuario.addActionListener(ctrlViewMain);
        jMenu1.add(miUsuario);

        miConfiguracion.setText("Listar UFVs");
        miConfiguracion.addActionListener(ctrlViewMain);
        jMenu1.add(miConfiguracion);
        jMenu1.add(jSeparator1);

        miRespaldar.setText("Respaldar BD");
        miRespaldar.addActionListener(ctrlViewMain);
        jMenu1.add(miRespaldar);

        miRestaurar.setText("Restaurar BD");
        miRestaurar.addActionListener(ctrlViewMain);
        jMenu1.add(miRestaurar);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public CommonDialog getCommonDialog() {
        return commonDialog;
    }

    public void setSw(boolean sw) {
        this.sw = sw;
    }

    public void setCommonDialog(CommonDialog commonDialog) {
        this.commonDialog = commonDialog;
    }

    public boolean isOnThemeUpdate() {
        return this.onThemeUpdate;
    }

    public ScreenDockStation getScreen() {
        return this.screen;
    }

    public DefaultDockable getDockableResponsable() {
        return this.dockableResponsable;
    }

    public DockFrontend getFrontend() {
        return this.frontend;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public JLabel getlUnidad() {
        return lUnidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public DefaultDockable getDockableOficina() {
        return dockableOficina;
    }

    public DefaultDockable getDockableEntidad() {
        return dockableEntidad;
    }

    public DefaultDockable getDockableUnidad() {
        return dockableUnidad;
    }

    public DefaultDockable getDockableAuxiliar() {
        return dockableAuxiliar;
    }

    public DefaultDockable getDockableActivoFijo() {
        return dockableActivoFijo;
    }

    public DefaultDockable getDockableRevaluo() {
        return dockableRevaluo;
    }

    public DefaultDockable getDockableBaja() {
        return dockableBaja;
    }

    public JLabel getlUFV() {
        return lUFV;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public DefaultDockable getDockableConfiguracion() {
        return dockableConfiguracion;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bActivoFijo;
    private javax.swing.JButton bAuxiliar;
    private javax.swing.JButton bConfiguracion;
    private javax.swing.JButton bEntidad;
    private javax.swing.JButton bGrupo;
    private javax.swing.JButton bOficina;
    private javax.swing.JButton bReport;
    private javax.swing.JButton bRespaldar;
    private javax.swing.JButton bResponsable;
    private javax.swing.JButton bRestaurar;
    private javax.swing.JButton bUnidad;
    private javax.swing.JButton bUsuario;
    private org.jc.util.ClockDigital clockDigital1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private com.l2fprod.common.swing.JTaskPane jTaskPane1;
    private javax.swing.JLabel lEntidad;
    private javax.swing.JLabel lUFV;
    private javax.swing.JLabel lUnidad;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JMenu mAdministracion;
    private javax.swing.JMenu mArchivo;
    private javax.swing.JMenu mConfiguracion;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem miAuxiliar;
    private javax.swing.JMenuItem miConfiguracion;
    private javax.swing.JMenuItem miEntidad;
    private javax.swing.JMenuItem miGrupo;
    private javax.swing.JMenuItem miOficina;
    private javax.swing.JMenuItem miRespaldar;
    private javax.swing.JMenuItem miResponsable;
    private javax.swing.JMenuItem miRestaurar;
    private javax.swing.JMenuItem miSalir;
    private javax.swing.JMenuItem miUnidad;
    private javax.swing.JMenuItem miUsuario;
    private javax.swing.JPopupMenu.Separator separador;
    private org.jdesktop.swingx.JXStatusBar statusBar;
    private com.l2fprod.common.swing.JTaskPaneGroup tpgActivosFijos;
    private com.l2fprod.common.swing.JTaskPaneGroup tpgAdministracion;
    private com.l2fprod.common.swing.JTaskPaneGroup tpgConfiguracion;
    private com.l2fprod.common.swing.JTaskPaneGroup tpgSistema;
    // End of variables declaration//GEN-END:variables

    private class CtrlViewMain implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object aux = e.getSource();
            boolean enter = false;
            if (aux.equals(miOficina) || aux.equals(bOficina)) {
                if (unidad.getIdUnidad() != 0) {
                    oficinaView.getOficinaTable().reload();
                    frontend.hide(dockableOficina);
                    frontend.show(dockableOficina);
                } else {
                    enter = true;
                }
            } else if (aux.equals(miResponsable) || aux.equals(bResponsable)) {
                if (unidad.getIdUnidad() != 0) {
                    responsableView.getResponsableTable().reload();
                    frontend.hide(dockableResponsable);
                    frontend.show(dockableResponsable);
                } else {
                    enter = true;
                }
            } else if (aux.equals(miEntidad) || aux.equals(bEntidad)) {
                EntidadView.entidadView.getEntidadTable().reload();
                frontend.hide(dockableEntidad);
                frontend.show(dockableEntidad);
            } else if (aux.equals(miUnidad) || aux.equals(bUnidad)) {
                UnidadView.unidadView.getUnidadTable().reload();
                frontend.hide(dockableUnidad);
                frontend.show(dockableUnidad);
            } else if (aux.equals(miUsuario) || aux.equals(bUsuario)) {
                usuarioView.getUsuarioTable().reload();
                frontend.hide(dockableUsuario);
                frontend.show(dockableUsuario);
            } else if (aux.equals(miGrupo) || aux.equals(bGrupo)) {
                grupoView.getGrupoTable().reload();
                frontend.hide(dockableGrupo);
                frontend.show(dockableGrupo);
            } else if (aux.equals(miAuxiliar) || aux.equals(bAuxiliar)) {
                if (unidad.getIdUnidad() != 0) {
                    auxiliarView.getAuxiliarTable().reload();
                    frontend.hide(dockableAuxiliar);
                    frontend.show(dockableAuxiliar);
                } else {
                    enter = true;
                }
            } else if (aux.equals(bActivoFijo)) {
                if (unidad.getIdUnidad() != 0) {
                    activoFijoView.getActivoFijoTable().reload();
                    frontend.hide(dockableActivoFijo);
                    frontend.show(dockableActivoFijo);
                } else {
                    enter = true;
                }
            } else if (aux.equals(miSalir)) {
                System.exit(0);
            } else if (aux.equals(bReport)) {
                frontend.hide(dockableSelectReport);
                frontend.show(dockableSelectReport);
            } else if (aux.equals(bConfiguracion) || aux.equals(miConfiguracion)) {
                frontend.hide(dockableConfiguracion);
                frontend.show(dockableConfiguracion);
            } else if (aux.equals(bRespaldar) || aux.equals(miRespaldar)) {
                commonDialog = new CommonDialog(ViewMain.this, true);
                commonDialog.add(respaldarForm);          
                respaldarForm.getTfDireccion().setText("");
                commonDialog.visibleDialog("Respaldar Base de Datos");
            } else if (aux.equals(bRestaurar) || aux.equals(miRestaurar)) {
                commonDialog = new CommonDialog(ViewMain.this, true);
                commonDialog.add(restaurarForm);
                restaurarForm.getTfDireccion().setText("");
                commonDialog.visibleDialog("Restaurar Base de Datos");
            }
            if (enter) {
                UnidadView.unidadView.getUnidadTable().reload();
                frontend.hide(dockableUnidad);
                frontend.show(dockableUnidad);
                JOptionPane.showMessageDialog(ViewMain.viewMain, "Tiene que seleccionar una unidad de la siguiente lista o Crear una nueva unidad");
            }
        }
    }
}