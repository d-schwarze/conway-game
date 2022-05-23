package de.conway.ui.statistic;

import de.conway.ui.Controller;
import de.conway.ui.TransferObject;

public class StatisticController extends Controller<StatisticView> {

	@Override
	public void init() {
		
		this.view = new StatisticView();
		
	}

	@Override
	public void onReceivedData(TransferObject obj) {
		// TODO Auto-generated method stub
		
	}

}
