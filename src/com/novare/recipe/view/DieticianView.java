package com.novare.recipe.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.novare.recipe.util.PrintHandler;

/**
 * @author malli
 *
 */
public class DieticianView extends BaseView {
	public DieticianView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Create Recipe", "View Recipe", "Update Recipe", "All Recipes");
	}

	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();
	}

	public String askRecipeName() throws IllegalArgumentException {
		System.out.print("Enter Recipe Name and press enter: ");
		String input = getUserTerminal().nextLine();
		if (input.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return input;
	}

	public List<Integer> askIngredients() {
		System.out.print("Choose mulitple options and press enter [e.g, 1,2]: ");
		String input = getUserTerminal().nextLine();
		if (input.isEmpty()) {
			throw new IllegalArgumentException();
		}
		boolean matches = Pattern.matches("^\\d+(,\\d+)*$", input);
		if (!matches) {
			throw new IllegalArgumentException();
		}
		return Arrays.asList(input.split(",")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
	}

	public String askRecipeSteps() {
		StringBuilder steps = new StringBuilder();
		System.out.println("Enter Steps and press enter [Type 'exit' to save the steps]:");

		while (true) {
			String input = getUserTerminal().nextLine();
			if (input.equalsIgnoreCase("exit")) {
				if (steps.toString().isEmpty()) {
					printInvalidOption();
					askRecipeSteps();
				}
				return steps.toString();
			}
			steps.append(input);
			steps.append("\n");
		}
	}

	public String askRecipeNameChange() {
		System.out.print("Want to change the recipe name [Yes/No] ?");
		if (askConfirmationYesOrNo()) {
			return askRecipeName();
		}
		return "";
	}

	public String askRecipeStepChange() {
		System.out.print("Want to change the recipe steps [Yes/No] ?");
		if (askConfirmationYesOrNo()) {
			return askRecipeSteps();
		}
		return "";
	}

	public List<Integer> askIngredientChange(List<?> ingredients) {
		System.out.print("Want to delete ingredient [Yes/No] ?");
		if (askConfirmationYesOrNo()) {
			setMenuOptionsInRow(ingredients);
			return askIngredients();
		} else {
			return Collections.emptyList();
		}
	}

	public boolean askIngredientAddition() {
		System.out.print("Want to add ingredients [Yes/No] ?");
		return askConfirmationYesOrNo();
	}

}
