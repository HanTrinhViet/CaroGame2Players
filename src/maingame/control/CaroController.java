package maingame.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maingame.model.CaroModel;
import maingame.model.CurrentPlayer;
import maingame.view.CaroView;

/**
 *
 * @author admin
 */
public class CaroController implements ActionListener {

    private CaroView caroView;
    private CaroModel caroModel;

    public CaroController() {
    }

    public CaroController(CaroView caroView, CaroModel caroModel) {
        this();
        this.caroView = caroView;
        this.caroModel = caroModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var eventListen = e.getActionCommand();
        if (eventListen.equals("NEW GAME")) {
            caroView.dispose();
            new CaroView(caroView.getLoginFrm(), caroView.getPlayerFirst(), caroView.getPlayerSecond());
        } else if (eventListen.equals("UNDO")) {
            caroView.undo();
        } else if (eventListen.equals("EXIT")) {
            caroView.exit();
        } else {
            String str = e.getActionCommand();
            int k = str.indexOf(32);
            int i = Integer.parseInt(str.substring(0, k));
            int j = Integer.parseInt(str.substring(k + 1, str.length()));

            if (caroModel.getTick()[i][j]) {
                caroView.updateBoard(i, j);
            }

            if (caroView.isWinCondition(i, j)) {
                caroView.getLblMessage().setBackground(Color.MAGENTA);
                caroView.getLblMessage().setText(getWinnerName(i, j) + " LÀ NGƯỜI THẮNG");
                for (i = 1; i <= caroView.getRow(); i++) {
                    for (j = 1; j <= caroView.getColumn(); j++) {
                        caroView.getBtnTiles()[i][j].setEnabled(false);
                    }
                }
                caroView.getBtnUndo().setEnabled(false);
                caroView.getBtnNewGame().setBackground(Color.YELLOW);
                if (caroModel.getCurrentPlayer() == CurrentPlayer.O) {
                    caroView.addScoreplayerOne();
                } else if (caroModel.getCurrentPlayer() == CurrentPlayer.X) {
                    caroView.addScoreplayerTwo();
                }
                caroView.updateInfoInterface();
            }
        }
    }

    private String getWinnerName(int i, int j) {
        if (caroView.getBtnTiles()[i][j].getText().equals("X")) {
            return caroView.getPlayerFirst().getFullName();
        } else if (caroView.getBtnTiles()[i][j].getText().equals("O")) {
            return caroView.getPlayerSecond().getFullName();
        }
        return "NULL";
    }

}
