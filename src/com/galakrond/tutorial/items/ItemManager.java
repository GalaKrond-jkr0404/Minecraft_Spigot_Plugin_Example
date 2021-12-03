package com.galakrond.tutorial.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack wand;
    // ItemStack wand 는 새로운 item 그 자체임

    public static void init() {
        createWand(); // 이런 식으로 init 에서 호출되도록 하면, 아이템을 초기화하고 Main Class 에서 부를 때 로드될 것임.

    }

    private static void createWand() {
        ItemStack item = new ItemStack(Material.STICK, 1);
        // 어떤 Material 인지 지정 후 Stack 가능한 최대 수 지정.. ex) 눈덩이 = 16, 대부분의 블록 = 64
        // 여기까지는 그저 Material 속 아이템일 뿐이기 때문에, 아래 코드들을 통해 속성 지정이 필요함.

        ItemMeta meta = item.getItemMeta(); // 속성 수정을 위한 아이템 메타 선언

        meta.setDisplayName("§b Stick of Truth");
        // § 특수문자가 궁금하다면 여기로 : https://www.digminecraft.com/lists/color_list_pc.php

        List<String> lore = new ArrayList<>();
        // 아이템의 설명을 적기 위해 List<String> 선언, 리스트 요소 한 개당 한 줄
        lore.add("§4A First Item That Gala Made");
        lore.add("§dRight Click to Make Explosion, Left Click to summon Wither Skull");
        meta.setLore(lore);

        meta.addEnchant(Enchantment.LUCK, 1, false);
        // 인챈트 추가 - 우리는 막대기를 만드니 인챈트 이펙트를 위해 쓸모없는 행운을 추가함.
        // 레벨은 int 값과 boolean 값을 통해 결정됨, bool 값이 false 일 경우 바닐라의 인챈트 레벨 제한을 돌파 가능.

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        // addItemFlags 를 통해서 인챈트된 내용을 숨김.

        item.setItemMeta(meta);
        // 아이템의 메타를 조금 전까지 설정한 것으로 옮김.

        wand = item;
        // createWand 는 private 으로 설정되었기 때문에, 우리는 앞에서 선언한 wand 에다 값을 넣어주어야 함.

    }



}
