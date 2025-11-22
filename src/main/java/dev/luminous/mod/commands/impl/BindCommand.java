package dev.luminous.mod.commands.impl;

import dev.luminous.mod.commands.Command;
import dev.luminous.Alien;
import dev.luminous.core.impl.CommandManager;
import dev.luminous.mod.modules.Module;
import dev.luminous.core.impl.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class BindCommand extends Command {

	public BindCommand() {
		super("bind", "[module] [key]");
	}

	@Override
	public void runCommand(String[] parameters) {
		if (parameters.length == 0) {
			sendUsage();
			return;
		}
		String moduleName = parameters[0];
		Module module = Alien.MODULE.getModuleByName(moduleName);
		if (module == null) {
			CommandManager.sendChatMessage("§4Could not find module!");
			return;
		}
		if (parameters.length == 1) {
			CommandManager.sendChatMessage("§fPlease specify a §bkey§fto be binded.");
			return;
		}
		String rkey = parameters[1];
		if (rkey == null) {
			CommandManager.sendChatMessage("§4An unknown error has occured.");
			return;
		}
		if (module.setBind(rkey.toUpperCase())) {
			CommandManager.sendChatMessage(module.getName() + "§f has binded to §r" + rkey.toUpperCase()); /* ExampleModuleName has binded to ExampleKey */
		}
	}

	@Override
	public String[] getAutocorrect(int count, List<String> seperated) {
		if (count == 1) {
			String input = seperated.get(seperated.size() - 1).toLowerCase();
			ModuleManager cm = Alien.MODULE;
			List<String> correct = new ArrayList<>();
			for (Module x : cm.modules) {
				if (input.equalsIgnoreCase(Alien.PREFIX + "bind") || x.getName().toLowerCase().startsWith(input)) {
					correct.add(x.getName());
				}
			}
			int numCmds = correct.size();
			String[] commands = new String[numCmds];

			int i = 0;
			for (String x : correct) {
				commands[i++] = x;
			}

			return commands;
		}
		return null;
	}
}
