package com.example.nekokamiko.entity.render;

import com.example.nekokamiko.entity.ChaseEntity;
import com.example.nekokamiko.entity.NekoEntity;
import com.example.nekokamiko.entity.model.ChaseModel;
import com.example.nekokamiko.entity.model.NekokamikoModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ChaseRender extends MobRenderer<ChaseEntity, ChaseModel<ChaseEntity>> {

    //テクスチャの場所を指定
    private static final ResourceLocation CHASE_ENTITY = new ResourceLocation("nekokamiko","textures/entity/mob/steve.png");

    //
    //モデル読み込む
    //EntityRendererManagerは、エンティティの描画に関連する機能を提供します。具体的には、
    // エンティティのモデルの読み込み、テクスチャの適用、アニメーションの制御、光源の計算などを担当します。
    // これにより、エンティティがゲーム内で正しく表示され、アニメーションや光源の効果が適用されるようになります。
    //
    //また、EntityRendererManagerは、エンティティの種類ごとに対応するEntityRendererを管理します。
    // EntityRendererは、特定のエンティティの描画方法を定義するクラスであり、
    // EntityRendererManagerは必要なときに適切なEntityRendererを呼び出してエンティティを描画します。
    //
    public ChaseRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ChaseModel<>(), 0.4F);

    }
    //
    //このメソッド呼ばれたらテクスチャ張られる
    //ResourceLocationは、フォーマットが「ネームスペース:パス」の形式で表されます。
    // ネームスペースはリソースのカテゴリを示し、通常はモッドやマインクラフトの内部カテゴリを表す識別子です。
    // パスはネームスペース内のリソースの場所や名前を指定します。
    //
    //たとえば、マインクラフトのデフォルトのテクスチャは「minecraft:textures/blocks/stone.png」というResourceLocationで表されます。
    // ここで、「minecraft」はネームスペースであり、「textures/blocks/stone.png」はリソースのパスです。
    // このResourceLocationを使用することで、ゲーム内でこのテクスチャを特定し、使用することができます。
    //
    @Override
    public ResourceLocation getEntityTexture(ChaseEntity entity) {
        return CHASE_ENTITY;
    }

    //
    //マイクラのpreRenderCallbackは、レンダリングの前に実行されるコールバック関数です。
    // このコールバックは、特定のエンティティやブロックのレンダリングが行われる前に、カスタムの処理や修正を行うために使用されます。
    //
    protected void preRenderCallback(ChaseEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {

        float f = 2.0F;
        matrixStackIn.scale(f,f,f);//全部０．８売
    }
}
