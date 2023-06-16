package com.example.nekokamiko.regi;

import com.example.nekokamiko.entity.render.ChaseEntityType;
import com.example.nekokamiko.entity.render.ChaseRender;
import com.example.nekokamiko.entity.render.NekoEntityTypes;
import com.example.nekokamiko.entity.render.NekoRender;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "nekokamiko", bus = Bus.MOD, value = Dist.CLIENT)

public class ClientEventBusSubscriber {


    //
    //FMLClientSetupEventの主な目的は、クライアント側でのMod関連の初期化処理を実行することです。具体的には、以下のような処理を行うことができます：
    //キーのバインド: FMLClientSetupEventを使用して、Mod固有のキーバインドを設定することができます。これにより、プレイヤーがカスタムのキー操作を割り当てることができます。
    //GUIの登録: カスタムのGUI（グラフィカルユーザーインターフェース）を登録するためにFMLClientSetupEventを使用することができます。
    // これにより、プレイヤーがカスタムのGUIを表示することができます。
    //テクスチャやモデルの読み込み: FMLClientSetupEventを使用して、Modのカスタムテクスチャやモデルをゲームに読み込むことができます。
    // これにより、エンティティやブロックの外観をカスタマイズすることができます。
    //
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent eventttt){

        renderRegister();
    }


    private static void renderRegister(){
        RenderingRegistry.registerEntityRenderingHandler(NekoEntityTypes.NEKO_ENTITY.get(), NekoRender::new);//モデルにテクスチャがくっつく
        RenderingRegistry.registerEntityRenderingHandler(ChaseEntityType.CHASE_ENTITY.get(), ChaseRender::new);//モデルにテクスチャがくっつく


    }


    //RenderingRegistryは、Minecraft Forgeのライブラリで使用されるクラスであり、エンティティやブロックの描画に関する登録や設定を行うための機能を提供します。
    //Minecraftでは、エンティティはゲーム内で移動したり相互作用したりする重要な要素です。エンティティの描画は、プレイヤーに対して視覚的な情報を提供する役割を果たしています。registerEntityRenderingHandlerメソッドを使用することで、自分のModに独自のエンティティを追加し、それに対するカスタムの描画処理を定義することができます。
    //
    //registerEntityRenderingHandlerメソッドは、以下のような形式で使用されます：
    //
    //java
    //Copy code
    //RenderingRegistry.registerEntityRenderingHandler(EntityClass.class, renderFactory);
    //EntityClassは、描画を設定したいエンティティクラスを指定します。
    //renderFactoryは、エンティティの描画を実際に行うためのファクトリメソッドまたはラムダ式を指定します。通常、このファクトリメソッド内でエンティティの描画に関する設定やレンダラーの作成を行います。

}
