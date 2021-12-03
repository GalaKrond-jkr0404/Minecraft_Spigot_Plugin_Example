package com.galakrond.tutorial.events;

import com.galakrond.tutorial.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Locale;

public class TutorialEvents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {            // 플레이어의 서버 입장 시 이벤트

        Player player = event.getPlayer(); // Player 형식의 player 를 event.GetPlayer 로 받아오기

        String playerNameRaw = player.getDisplayName();

        // 아까 그대로 받아온 player 는 일반 변수가 아니므로, getDisplayName()을 통해서 일반적으로 보여지는 이름을 저장.

        player.sendMessage(ChatColor.GREEN + "Welcome" + playerNameRaw + "!");
        // playerNameRaw 를 통해서 이름 부르기, 참고로 그냥 Player 형식을 집어넣어두면 CraftPlayer{name=이름} 형식으로 나옴.


    }

    @EventHandler
    public static void onPlayerWalk(PlayerMoveEvent event) {         // 플레이어가 걷고 있을 때의 이벤트
        Player player = event.getPlayer();
        int x = player.getLocation().getBlockX();                    // 플레이어의 현재 X좌표
        int y = player.getLocation().getBlockY();                    // 플레이어의 현재 Y좌표
        int z = player.getLocation().getBlockZ();                    // 플레이어의 현재 Z좌표

        Material block = player.getWorld().getBlockAt(x, y - 1, z).getType();
        // Material 은 잘 모르겠는데, 어쨌든 block 은 player 의 발 밑에 있는 (= 이게 y-1을 Y좌표 값에 집어넣는 이유)의 타입을 가지고옴 ex...) STONE, LADDER.... etc.
        if (block == Material.STONE) {          //아까 발 밑에 있었던 블록의 타입이 'STONE' 인가?
            player.sendMessage(ChatColor.RED + "Bruh you are standing on stones");                   // 돌을 밟지 말아주세요
        }
    }

    @EventHandler
    public static void onClick(PlayerInteractEvent event) { // wand 를 들고 있는 상태에서 onClick 시 발생하는 이벤트
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) { // 블록을 우클릭한 이벤트
            if (event.getItem() != null){ // 아이템의 존재 여부
                if (event.getItem().getItemMeta().equals(ItemManager.wand.getItemMeta())){ // 그냥 getItem 을 할 경우에는 일반 아이템으로 간주되므로, 새로 만든 아이템은 ItemMeta 로 비교해야함
                    Player player = event.getPlayer();
                    Location location = player.getTargetBlock(null,15).getLocation(); // 폭발할 위치 선정, 현재 player 가 타게팅한 블록을 15블록 이내에서 받아옴.
                    player.getWorld().createExplosion(location, 2.0f); // 아까 위치 선정한 곳에서 강도 2f 인 폭발 생성, 참고로 TNT 는 4f
                    player.sendMessage("§6Get Boom");
                }
            }

        }

        else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)  { // 아무 곳이나 좌클릭했을 때 발생하는 이벤트
            if (event.getItem() != null){
                if (event.getItem().getItemMeta().equals(ItemManager.wand.getItemMeta())){
                    Player player = event.getPlayer();
                    Location location = player.getLocation();
                    location.setY(location.getY() + 4); // 그냥 player.location 에 뒀다가는 조금이라도 바닥을 바라보면 위더 해골이 바로 밑에서 폭발함.
                    player.getWorld().spawnEntity(location, EntityType.WITHER_SKULL); // 위더 해골 투사체는 별다른 설정 없이도 자동으로 앞으로 나가기 때문에, 그냥 소환하기에는 좋은 편임.
                    player.sendMessage("§1See The Power of Plugins! ");
                }
            }

        }
    }

}
