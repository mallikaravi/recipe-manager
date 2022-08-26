package com.novare.recipe.action;

import com.novare.recipe.controller.DieticianController;
import com.novare.recipe.service.impl.DieticianServiceImpl;
import com.novare.recipe.util.MenuContext;
import com.novare.recipe.view.DieticianView;

/**
 * This class extends the base menu action class
 *
 */
public class UpdateMenuAction extends BaseMenuAction {
	/**
	 * This method instantiates the dietician view,controller and model.This is the
	 * action of dietician that prints update recipe
	 * @throws Exception
	 */
	public UpdateMenuAction() throws Exception {
		DieticianServiceImpl model = new DieticianServiceImpl();
		DieticianView view = new DieticianView("Update Recipe menu options:");
		DieticianController controller = new DieticianController(model, view);
		controller.requestUserInput(MenuContext.UPDATE_RECIPE);

	}

}
