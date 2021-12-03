package com.galakrond.tutorial;

import com.galakrond.tutorial.commands.TutorialCommands;
import com.galakrond.tutorial.events.TutorialEvents;
import com.galakrond.tutorial.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public class Tutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        TutorialCommands commands = new TutorialCommands();
        getServer().getPluginManager().registerEvents(new TutorialEvents(), this);
        // 서버의 플러그인 매니저를 받아오고, 새 이벤트로 TutorialEvents()를 등록. plugin? 당연히 얘 자신이니까 this 적어두기

        getCommand("heal").setExecutor(commands); // getCommand 를 통한 "heal" 커맨드 등록

        getCommand("feed").setExecutor(commands);

        getCommand("golemdrop").setExecutor(commands);

        getCommand("farmtime").setExecutor(commands);

        getCommand("givewand").setExecutor(commands);


        ItemManager.init(); // ItemManager 의 아이템을 모두 초기화, 로드.





        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "-Testplugin- Hello World!");
        // 플러그인 활성화시. 콘솔에 메시지 보내기
    }


    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "-Testplugin- GoodBye!");
    } // 플러그인 비활성화시, 콘솔에 메시지 보내기기


}
