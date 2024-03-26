<template>
  <div>
    <h2>{{ name }}</h2>
    <ul>
    <li v-for="(film, index) in soovitatudFilmid" :key="index">
            {{ film }}
          </li>
    </ul>

  </div>
</template>

<script>
import SuggestionService from '../services/SuggestionService'

export default {
  data() {
    return {
      name: 'soovitatudFilmid',
      soovitatudFilmid: []
    };
  },
  methods: {
    getSoovitused(){
        return SuggestionService.getSoovitused().then((response) =>{
            this.soovitatudFilmid = response.data;
             });
           },

  },
  mounted() {
      this.getSoovitused();
    }
}
</script>

