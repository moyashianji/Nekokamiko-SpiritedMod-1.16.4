package com.example.nekokamiko.main;

import com.example.nekokamiko.entity.render.ChaseEntityType;
import com.example.nekokamiko.entity.render.NekoEntityTypes;
import com.example.nekokamiko.event.PickUpEvent;
import com.example.nekokamiko.item.ItemInit;
import com.example.nekokamiko.regi.NekoBlocks;
import com.example.nekokamiko.regi.NekoModTab2;
import com.example.nekokamiko.timer.TimeEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.example.nekokamiko.timer.TimeEvent.timerti;


@Mod(Nekokamiko.MOD_ID)
public class Nekokamiko {

    public static final String MOD_ID = "nekokamiko";
    public static final ItemGroup NEKO_TAB = new NekoModTab2();

    public Nekokamiko(){


        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        //
        //inecraft Forgeでは、ゲーム内の様々なイベントが発生します。例えば、ブロックの設置や破壊、エンティティのスポーン、アイテムの使用などがイベントとして扱われます。
        // これらのイベントは、ゲームプレイのカスタマイズや機能の追加、特定の条件での挙動の変更などに使用されます。
        //
        //IEventBusは、イベントの登録やイベントリスナーの管理を行うためのインターフェースです。
        // 通常、Minecraft ForgeのModクラスでIEventBusを使用してイベントの登録や処理を行います。
        //
        //Minecraft Forgeでは、Modの読み込みや初期化などのタイミングでさまざまな処理を行う必要があります。
        // FMLJavaModLoadingContextは、これらの処理を実行するためのコンテキストを提供します。
        //具体的には、FMLJavaModLoadingContextは以下のような機能を提供します：
        //Modの読み込みイベントの管理: FMLJavaModLoadingContextは、Modの読み込みイベントを管理します。
        // Modの読み込みイベントは、Modの初期化や設定のロードなどのタイミングで発生します。
        // FMLJavaModLoadingContextを使用することで、Modの読み込みイベントに対する処理を簡単に登録できます。
        //Modの情報へのアクセス: FMLJavaModLoadingContextは、Modの情報にアクセスするための便利なメソッドを提供します。
        // ModのIDやModのバージョンなどの情報にアクセスすることができます。
        //イベントバスの取得: FMLJavaModLoadingContextは、Modのイベントバスを取得するためのメソッドを提供します。
        // イベントバスを使用すると、Modのイベントリスナーの登録やイベントの送信を行うことができます。
        //
        //イベントは、ゲーム内で特定のアクションや条件が満たされたときに発生するものです。
        // 例えば、ブロックの設置、エンティティのスポーン、アイテムの使用などがイベントとして扱われます。
        // EventBusは、これらのイベントを受け取り、それに応じて登録されたイベントリスナーに処理を委譲します。
        //Minecraft Forgeでは、EventBusを使用してModのイベントの登録や処理を行います。
        // Mod開発者は、EventBusを介してゲーム内のさまざまなイベントに対するカスタム処理を実装できます。
        //EventBusの基本的な使用方法は次のとおりです：
        //イベントリスナーの登録: Modの初期化時に、EventBusにイベントリスナーを登録します。イベントリスナーは、特定のイベントが発生したときに呼び出されるメソッドです。
        //イベントの送信: ゲーム内で特定のアクションや条件が発生した場合、対応するイベントをEventBusに送信します。EventBusは、送信されたイベントを登録されたイベントリスナーに対して通知します。
        //

        bus.addListener(this::setup);

        NekoBlocks.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        NekoEntityTypes.REGISTER.register(bus);
        ChaseEntityType.CHASE_REGISTER.register(bus);
        YourEventHandler.register();
        PickUpEvent.register();

        onRespawn.register();

        MinecraftForge.EVENT_BUS.register(this);

        System.out.println("あああああああいあいあいあいあ");


    }



    //
    //Minecraftは、クライアント側とサーバー側の両方で実行されます。FMLCommonSetupEventは、クライアントとサーバーの両方で共通の初期化処理を実行するために使用されます。
    // このイベントは、Modの初期化の最初の段階で発生し、次のような処理を行うことができます：
    //
    //ブロックやアイテムの登録: FMLCommonSetupEventを使用して、Modのブロックやアイテムをゲーム内に登録することができます。
    //
    //レシピの追加: FMLCommonSetupEventを使用して、Mod固有のクラフトや調理のレシピを追加することができます。
    //
    //サーバーサイドの設定の初期化: サーバーサイドでの設定の初期化を行うためにFMLCommonSetupEventを使用することができます。
    // たとえば、Modのカスタムのワールド生成ルールやブロックの動作を設定することができます。
    //
    private void setup(final FMLCommonSetupEvent event){


    }





}