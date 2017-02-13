package br.com.jeffque.tc.itemcontainer.etc;

import br.com.jeffque.io.FileWrapper;
import br.com.jeffque.io.StreamWrapper;
import br.com.jeffque.tc.itemcontainer.Restaurante;
import br.com.jeffque.tc.ui.BaseContainer;
import br.com.jeffque.tc.ui.ItemContainer;
import totalcross.io.BufferedStream;
import totalcross.io.File;
import totalcross.io.IOException;
import totalcross.json.JSONObject;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.FocusListener;
import totalcross.ui.event.PressListener;

public class RestauranteInfo extends BaseContainer {
	ItemContainer<Restaurante> restauranteScreen; // TODO usar um padrão mais bacana que esse... quem sabe com callback?
	Label qnt;
	String fileName = "restaurantes.json";
	
	public RestauranteInfo(ItemContainer<Restaurante> restauranteScreen) {
		super();
		
		this.restauranteScreen = restauranteScreen;
	}
	
	@Override
	public void initUI() {
		super.initUI();
		qnt = new Label("Quantidade: " + restauranteScreen.getItems().size());
		
		Button limparButton = new Button("Limpar");
		limparButton.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent e) {
				limpar();
			}
		});
		
		Button salvarButton = new Button("Salvar");
		salvarButton.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent e) {
				try {
					salvar();
				} catch (IOException e1) {
					Vm.debug("Erro ao salvar, colocar um modal aqui para falar com o usuário");
					e1.printStackTrace();
				}
			}
		});
		
		add(qnt, LEFT, AFTER, FILL, PREFERRED);
		
		Label fileNameLbl = new Label("Nome do arquivo: ");
		Edit fileNameEdt = new Edit(fileName);
		fileNameEdt.setText(fileName);
		
		fileNameEdt.addFocusListener(new FocusListener() {
			
			@Override
			public void focusOut(ControlEvent e) {
				fileName = fileNameEdt.getText();
			}
			
			@Override
			public void focusIn(ControlEvent e) {
			}
		});
		
		add(fileNameLbl, LEFT, AFTER, PREFERRED, PREFERRED);
		add(fileNameEdt, AFTER, SAME, FILL, SAME);
		
		add(salvarButton, LEFT, AFTER, FILL, PREFERRED);
		add(limparButton, LEFT, AFTER, FILL, PREFERRED);
	}
	
	public String getFileName() {
		return fileName;
	}
	
	private void salvar() throws IOException {
		try (
				FileWrapper f = new FileWrapper(new File(getFileName(), File.CREATE_EMPTY));
				StreamWrapper stream = new StreamWrapper(new BufferedStream(f, BufferedStream.WRITE))
			) {
			boolean first = true;
			stream.writeBytes("[");
			
			for (Restaurante r: restauranteScreen.getItems()) {
				if (first) {
					first = false;
				} else {
					stream.writeBytes(",".getBytes());
				}
				JSONObject json = new JSONObject(r);
				stream.writeBytes(json.toString(4));
			}
			stream.writeBytes("]");
		}
	}
	
	private void carregar() {
		// TODO implementar
	}
	
	private void limpar() {
		qnt.setText("Quantidade: 0");
		qnt.repaintNow();
		restauranteScreen.reset();
	}
}
