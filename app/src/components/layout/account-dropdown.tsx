import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';
import { LogOut, Star, ShoppingBag, UserPen } from 'lucide-react';
import { UserDetails } from '@/types/user';
import { ReactNode } from 'react';

interface AccountDropdownProps {
  trigger: ReactNode;
  userDetails: UserDetails | null;
}
function AccountDropdown({ trigger, userDetails }: AccountDropdownProps) {
  console.log('user details = ==== ', userDetails);
  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>{trigger}</DropdownMenuTrigger>
      <DropdownMenuContent className="w-56">
        <DropdownMenuLabel>{userDetails?.username}</DropdownMenuLabel>
        <DropdownMenuSeparator />
        <DropdownMenuGroup>
          <DropdownMenuItem>
            {' '}
            <UserPen />
            Profile
          </DropdownMenuItem>
          <DropdownMenuItem>
            <ShoppingBag /> Orders{' '}
          </DropdownMenuItem>
          <DropdownMenuItem>
            <Star /> Reviews
          </DropdownMenuItem>
        </DropdownMenuGroup>

        <DropdownMenuItem>
          <LogOut />
          Log out
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

export default AccountDropdown;
