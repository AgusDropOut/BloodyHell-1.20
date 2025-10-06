package net.agusdropout.bloodyhell.particle.ParticleOptions;

import com.mojang.brigadier.StringReader;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleBlockParticleOptions implements ParticleOptions {

    public static final Codec<SimpleBlockParticleOptions> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    BlockState.CODEC.fieldOf("blockstate").forGetter(SimpleBlockParticleOptions::getBlockState),
                    Codec.INT.fieldOf("size").forGetter(SimpleBlockParticleOptions::getInitialSize),
                    Codec.BOOL.fieldOf("incremental").forGetter(SimpleBlockParticleOptions::hasIncrementalSize)
            ).apply(instance, SimpleBlockParticleOptions::new));

    private final BlockState state;
    private final int initialSize;
    private final boolean hasIncrementalSize;

    public SimpleBlockParticleOptions(BlockState state, int initialSize, boolean hasIncrementalSize) {
        this.state = state;
        this.initialSize = initialSize;
        this.hasIncrementalSize = hasIncrementalSize;
    }

    public BlockState getBlockState() {
        return state;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public boolean hasIncrementalSize() {
        return hasIncrementalSize;
    }

    @Override
    public ParticleType<?> getType() {
        return ModParticles.SIMPLE_BLOCK_PARTICLE.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buf) {
        buf.writeVarInt(Block.getId(state));
        buf.writeVarInt(initialSize);
        buf.writeBoolean(hasIncrementalSize);
    }

    @Override
    public String writeToString() {
        return BuiltInRegistries.BLOCK.getKey(state.getBlock()) +
                " size=" + initialSize +
                " incremental=" + hasIncrementalSize;
    }

    public static final Deserializer<SimpleBlockParticleOptions> DESERIALIZER =
            new Deserializer<>() {
                @Override
                public SimpleBlockParticleOptions fromCommand(ParticleType<SimpleBlockParticleOptions> type, StringReader reader) {
                    throw new UnsupportedOperationException("Command not supported for SimpleBlockParticleOptions");
                }

                @Override
                public SimpleBlockParticleOptions fromNetwork(ParticleType<SimpleBlockParticleOptions> type, FriendlyByteBuf buf) {
                    BlockState state = Block.stateById(buf.readVarInt());
                    int size = buf.readVarInt();
                    boolean incremental = buf.readBoolean();
                    return new SimpleBlockParticleOptions(state, size, incremental);
                }
            };
}

