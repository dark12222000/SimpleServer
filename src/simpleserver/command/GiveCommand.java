/*******************************************************************************
 * Open Source Initiative OSI - The MIT License:Licensing
 * The MIT License
 * Copyright (c) 2010 Charles Wagner Jr. (spiegalpwns@gmail.com)
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 ******************************************************************************/
package simpleserver.command;

import simpleserver.Player;

public class GiveCommand extends AbstractCommand {
  private int offset;

  public GiveCommand() {
    this("give", 0);
  }

  protected GiveCommand(String name, int offset) {
    super(name);

    this.offset = offset;
  }

  @Override
  public void execute(Player player, String message) {
    String[] arguments = extractArguments(message);

    Player target = getTarget(player, arguments);
    if (target == null) {
      return;
    }

    if (arguments.length > offset) {
      String item = arguments[offset];

      String amount = null;
      if (arguments.length > offset + 1) {
        amount = arguments[offset + 1];
      }

      target.give(item, amount);
      player.getServer().adminLog.addMessage("User " + player.getName()
          + " used giveplayer:\t " + target.getName() + "\t" + item + "\t("
          + amount + ")");
    }
    else {
      player.addMessage("\302\247cNo item or amount specified!");
    }
  }

  protected Player getTarget(Player player, String[] arguments) {
    return player;
  }
}
