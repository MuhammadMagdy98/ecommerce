import { configureStore } from '@reduxjs/toolkit';
import userReducer from '@/slices/user-slices';

export const store = configureStore({
  reducer: { userDetails: userReducer },
});

export type RootState = ReturnType<typeof store.getState>;
// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch;
