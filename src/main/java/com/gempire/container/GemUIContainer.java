package com.gempire.container;

import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import com.gempire.tileentities.InjectorTE;
import com.mojang.datafixers.util.Pair;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.system.CallbackI;

import java.util.List;
import java.util.Objects;

public class GemUIContainer extends Container {
    public static final int HOTBAR_SLOT_COUNT = 9;
    public static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    public static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    public static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    public static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public static final int VANILLA_FIRST_SLOT_INDEX = 0;
    public static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    public static final int TE_INVENTORY_SLOT_COUNT = EntityGem.NUMBER_OF_SLOTS;

    public static final int TILE_INVENTORY_YPOS = 20;
    public static final int PLAYER_INVENTORY_YPOS = 51;

    public final IWorldPosCallable canInteract;

    public final EntityGem gem;

    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");

    private static final ResourceLocation[] ARMOR_SLOT_TEXTURES = new ResourceLocation[]{EMPTY_ARMOR_SLOT_BOOTS, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_HELMET};
    private static final EquipmentSlotType[] VALID_EQUIPMENT_SLOTS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};

    public GemUIContainer(int windowID, PlayerInventory playerInventory, EntityGem gem) {
        super(ModContainers.GEM_UI_CONTAINER.get(), windowID);
        this.gem = gem;
        this.canInteract = IWorldPosCallable.of(this.gem.world, this.gem.getPosition());

        /*this.addSlot(new Slot((IInventory)this.gem, TankTE.BUCKET_INPUT_SLOT_INDEX, 78, 50));
        this.addSlot(new Slot((IInventory)this.gem, TankTE.BUCKET_OUTPUT_SLOT_INDEX, 96, 50));*/

        //INITIALIZE ARMOR SLOTS
        /*for(int k = 0; k < 4; ++k) {
            final EquipmentSlotType equipmentslottype = VALID_EQUIPMENT_SLOTS[k];
            this.addSlot(new Slot(playerInventory, 63 + k, 11 + k*18, 65));
        }*/

        //INITIALIZE GEM INVENTORY HERE
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 9; col++){
                this.addSlot(new Slot(gem, col + row * 9 + 36, 11 + col * 18, 93 - (4 - row) * 18 - 10));
            }
        }

        //PLAYER INVENTORY
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 9; col++){
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 56 + col * 18, 250 - (4 - row) * 18 - 10));
            }
        }

        //PLAYER HOTBAR
        for(int col = 0; col < 9; col++){
            this.addSlot(new Slot(playerInventory, col, 56 + col * 18, 226));
        }
    }

    public GemUIContainer(int windowID, PlayerInventory playerInventory, PacketBuffer extraData){
        this(windowID, playerInventory, GemUIContainer.getGem(playerInventory, extraData));
    }

    public static EntityGem getGem(PlayerInventory playerInventory, PacketBuffer extraData){
        Objects.requireNonNull(playerInventory, "Player Inventory can not be null");
        Objects.requireNonNull(extraData, "Data Packet can not be null");
        BlockPos pos = extraData.readBlockPos();
        AxisAlignedBB aabb = new AxisAlignedBB(pos.getX() - .5f, pos.getY() - .5f, pos.getZ() - .5f, pos.getX() + .5f, pos.getY() + .5f, pos.getZ() + .5f);
        List<EntityGem> gems = playerInventory.player.world.getEntitiesWithinAABB(EntityGem.class, aabb);
        for(EntityGem candidate : gems){
            System.out.println(candidate.OWNERS);
            System.out.println(playerInventory.player.getUniqueID());
            return candidate;
        }
        throw new IllegalStateException("Entity is not correct");
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return gem.isOwner(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        System.out.println("sus");
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();
            if(index < EntityGem.NUMBER_OF_SLOTS && !this.mergeItemStack(slotStack, EntityGem.NUMBER_OF_SLOTS, this.inventorySlots.size(), true)){
                return ItemStack.EMPTY;
            }
            if(!this.mergeItemStack(slotStack, 0, EntityGem.NUMBER_OF_SLOTS, false)){
                return ItemStack.EMPTY;
            }
            if(slotStack.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            }
            else{
                slot.onSlotChanged();
            }
        }
        return stack;
    }
}
