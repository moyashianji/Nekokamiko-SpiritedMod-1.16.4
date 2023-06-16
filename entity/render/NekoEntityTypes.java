package com.example.nekokamiko.entity.render;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.example.nekokamiko.entity.NekoEntity;

import java.util.function.BiFunction;

public class NekoEntityTypes {

    //
    //DeferredRegisterは、この問題を解決するために導入されました。
    // DeferredRegisterを使用すると、登録を遅延させ、必要な要素が実際に使用されるタイミングで登録を行うことができます。
    // これにより、要素の登録順序の問題や依存関係の問題を回避することができます。
    //具体的には、DeferredRegisterは以下のような機能を提供します：
    //登録の遅延: DeferredRegisterを使用すると、ブロックやアイテムの登録を遅延させることができます。
    // これにより、他の要素に依存している場合でも適切な順序で登録が行われます。
    //登録のタイプセーフ: DeferredRegisterは、登録する要素の型を指定することができます。これにより、誤った型の要素を登録することを防ぐことができます。
    //登録の簡素化: DeferredRegisterは、登録処理を簡素化し、冗長なコードを削減します。必要な要素を登録するためのメソッドやインターフェースを提供します。
    //

    //
    //ForgeRegistriesは、Minecraft Forgeのライブラリで使用されるクラスです。
    // ForgeRegistriesは、ゲーム内の要素（ブロック、アイテム、エンティティなど）のレジストリ（登録表）へのアクセスを提供します。
    //
    //Minecraft Forgeでは、ゲーム内の要素を登録する際にレジストリを使用します。
    // レジストリは、ゲーム内の要素を一元管理し、一意の識別子（RegistryKey）に基づいて要素にアクセスするためのインターフェースを提供します。
    //
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, "nekokamiko");

    //
    //gistryObjectは、Minecraft Forgeのライブラリで使用されるジェネリッククラスです。
    // RegistryObjectは、ゲーム内の要素（ブロック、アイテム、タイルエンティティなど）への参照をラップし、要素への安全なアクセスを提供します。
    //
    //Minecraft Forgeでは、要素を登録する際にRegistryObjectを使用することが一般的です。
    // RegistryObjectは、要素のレジストリへの参照を保持し、必要なときに要素を取得することができます。
    // また、RegistryObjectは遅延評価され、要素が実際に必要になるまで要素のインスタンスを作成しないため、効率的なメモリ管理をサポートします。
    //
    public static final RegistryObject<EntityType<NekoEntity>> NEKO_ENTITY = nekoentity("neko_mob", NekoEntity::new);
    public static  <T extends Entity>RegistryObject<EntityType<T>> nekoentity(String id, BiFunction<EntityType<T>, World, T> function){

        EntityType<T> type = EntityType
                .Builder
                .create(function::apply,
                        EntityClassification.MISC)
                .size(0.5F,1.6F)
                .trackingRange(32)
                .build(id);

        return REGISTER.register(id, ()->type);
    }
}
//BiFunctionは、Javaの関数型インターフェースの一つです。BiFunctionは、2つの引数を受け取り、結果を返す関数の形式を持っています

//EntityClassificationは、主にエンティティの生成やスポーン条件、AIの制御などで使用されます。エンティティを特定の分類に属するように設定することで、ゲーム内の動作や挙動を制御することができます。
//
//以下は、マイクラで使用される主なEntityClassificationの定数です：
//
//CREATURE: 動物やペットなど、友好的なエンティティを分類します。
//MONSTER: ホストアイルダーなどの敵対的なモンスターエンティティを分類します。
//AMBIENT: 昆虫や小動物など、周囲の環境に影響を与えないエンティティを分類します。
//WATER_CREATURE: 水中に生息するエンティティ（魚やイカなど）を分類します。
//WATER_AMBIENT: 水中の環境に存在するエンティティ（クラゲや水草など）を分類します。
//MISC: その他の特定のカテゴリに属さないエンティティを分類します。
//これらの分類は、エンティティのスポーン条件やAIの設定などに使用され、特定のエンティティがどのように振る舞うかを制御します。例えば、MONSTERに分類されたエンティティはプレイヤーに攻撃を仕掛ける敵対的な行動を取る一方、CREATUREに分類されたエンティティはプレイヤーや他のエンティティに対して友好的な行動を取る傾向があります。