<template>
  <div id="nav">
    <div><router-link to="/DBR/recommend-advisor" replace>Recommend Advisor</router-link></div>
  </div>
</template>

<script>
export default {
  components: {
  },
  name: 'DBRLeftMenu',
  methods:{
    select: function(args) {
      this.$router.push(args.item.text);
    }
  }
};
</script>

<style>
left-menu-dbr{
  width: 200px;
  padding: 100px;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>