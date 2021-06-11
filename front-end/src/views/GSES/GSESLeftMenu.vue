<template>
  <div class="left-menu-gses">
    <div id="nav">

    </div>
  </div>
</template>

<script>
export default {
  components: {
  },
  methods:{
    select: function(args) {
      this.$router.push(args.item.text);
    }
  }
};
</script>

<style>
left-menu-gses{
  width: 200px;
  padding: 100px;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>