package com.galakrond.tutorial.commands;

import com.galakrond.tutorial.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.Locale;

public class TutorialCommands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) { // 요청자가 Player 상속이 아니야? = 콘솔이나 커맨드 블록, 플레이어 아닌 대상이 명령어 실행 시
            sender.sendMessage(("only players can use That Command!"));
            return true;
        }

        Player player = (Player) sender; // player = 요청자

        // /heal
        if (cmd.getName().equalsIgnoreCase("heal")) { // if 커맨드가 "heal"일 때
            String playerNameRaw = player.getDisplayName();
            double maxhealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue(); // player.getAttribute 를 통해 요청자의 최대 체력을 double 타입 maxhealth 에 저장.

            player.setHealth(maxhealth); // setHealth 를 통한 player 의 체력 = 최대 체력
            player.sendMessage(ChatColor.GREEN + "You've got Healed," + playerNameRaw );
            return true;
        }

        // /feed
        else if (cmd.getName().equalsIgnoreCase("feed")) {
            player.setFoodLevel(20); // 허기 레벨의 최댓값 == 20

            player.sendMessage(ChatColor.GREEN + "nom nom.");
            return true;
        }

        // /golemdrop
        else if (cmd.getName().equalsIgnoreCase("golemdrop")) {
            World w = Bukkit.getWorld("world"); //if the event has a player object use that to get the world
            Location location = player.getLocation();
            location.setY(location.getBlockY() + 100);


            Entity entity = w.spawnEntity(location, EntityType.IRON_GOLEM);

            location.setY(location.getBlockY() - 30);

            Entity tnt= w.spawnEntity(location, EntityType.PRIMED_TNT);
            TNTPrimed tnt2 = (TNTPrimed) tnt;
            tnt2.setFuseTicks(100);

            player.sendMessage(ChatColor.RED + "wooooosh");


            return true;
        }

        // /farmtime cow 5
        else if (cmd.getName().equalsIgnoreCase("farmtime")) {

            if (args.length >= 2) { // 명령어의 인자값이 2개 이상이면

                try {
                    EntityType entity = EntityType.valueOf(args[0].toUpperCase()); // 0번째 인자에서 엔티티 이름 받아오기

                    int amount = Integer.parseInt((args[1])); // 1번째 인자에서 정수 받아오기

                    for (int i = 0; i < amount; i++ ) { // 아까 받아온 정수만큼 소환
                        player.getWorld().spawnEntity(player.getLocation(), entity);

                    }

                }

                catch (IllegalArgumentException e) { // try | catch 문을 통해서 에러 잡아내고 리턴하기
                    player.sendMessage(ChatColor.RED + "please enter a valid entity");
                }

            }


            else { // 예외
                player.sendMessage(ChatColor.RED + "farmtime <mob> <amount>");
            }

        }



        else if (cmd.getName().equalsIgnoreCase("givewand")) {
            // ItemManager 가 아닌 여기서도 아이템을 생성할 수 는 있다.
            // 하지만, 커맨드를 돌릴 때마다 계속해서 새로운 아이템을 생성하기 때문에, 서버 자원의 낭비!

            player.getInventory().addItem(ItemManager.wand); // 인벤토리 소매넣기



       }






        return true;
    }


}
