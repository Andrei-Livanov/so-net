<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title class="t">Sonet</v-toolbar-title>
      <v-btn
          text
          v-if="profile"
          :disabled="$route.path === '/'"
          @click="showMessages" title="Список сообщений"
      >
        Messages
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn
          text
          v-if="profile"
          :disabled="$route.path === '/user'"
          @click="showProfile" title="Профиль пользователя"
      >
        {{ profile.name }}
      </v-btn>
      <v-btn v-if="profile" icon href="/logout" title="Выйти">
        <v-icon>{{ exitToAppIcon }}</v-icon>
      </v-btn>
    </v-app-bar>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
import {mapState, mapMutations} from 'vuex';
import {mdiExitToApp} from '@mdi/js'
import {addHandler} from 'util/ws';

export default {
  computed: mapState(['profile']),
  methods: {
    ...mapMutations([
      'addMessageMutation',
      'updateMessageMutation',
      'removeMessageMutation',
      'addCommentMutation'
    ]),
    showMessages() {
      this.$router.push('/');
    },
    showProfile() {
      this.$router.push('/user');
    }
  },
  data() {
    return {
      exitToAppIcon: mdiExitToApp
    }
  },
  created() {
    addHandler(data => {
      if (data.objectType === 'MESSAGE') {
        switch (data.eventType) {
          case 'CREATE':
            this.addMessageMutation(data.body);
            break;
          case 'UPDATE':
            this.updateMessageMutation(data.body);
            break;
          case 'REMOVE':
            this.removeMessageMutation(data.body);
            break;
          default:
            console.error(`Looks like the event type is unknown "${data.eventType}".`);
        }
      } else if (data.objectType === 'COMMENT') {
        switch (data.eventType) {
          case 'CREATE':
            this.addCommentMutation(data.body);
            break;
          default:
            console.error(`Looks like the event type is unknown "${data.eventType}".`);
        }
      } else {
        console.error(`Looks like the object type is unknown "${data.objectType}".`);
      }
    })
  },
  beforeMount() {
    if (!this.profile) {
      this.$router.replace('/auth')
    }
  }
}
</script>

<style>
.t {
  color: green;
  font-family: 'Roboto', sans-serif;
}
</style>
