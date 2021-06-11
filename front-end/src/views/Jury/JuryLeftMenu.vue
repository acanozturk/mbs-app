<template>
  <div class="left-menu-jury">
    <div id="nav">
      <div>
        <router-link to="/jury/thesis-management" replace>Thesis Management</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  methods: {
    select: function (args) {
      this.$router.push(args.item.text);
    }
  }
};
</script>

<style>
left-menu-jury {
  width: 200px;
  padding: 100px;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>