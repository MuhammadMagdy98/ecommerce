import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Heart, Eye } from 'lucide-react';

interface ProductCardProps {
  name: string;
  price: number;
  image: string;
  discount?: number; // Optional discount percentage
}

function ProductCard({ name, price, image, discount }: ProductCardProps) {
  // Calculate discounted price if discount exists
  const discountedPrice = discount ? price - price * (discount / 100) : price;

  return (
    <Card className="relative h-96 w-64 border shadow-md hover:shadow-lg transition-shadow m-2">
      {/* Top-Right Icons */}
      <div className="absolute top-2 right-2 flex space-x-2">
        <button className="p-2 bg-white rounded-full shadow-md hover:bg-gray-100">
          <Heart className="w-4 h-4 text-red-500" />
        </button>
        <button className="p-2 bg-white rounded-full shadow-md hover:bg-gray-100">
          <Eye className="w-4 h-4 text-gray-700" />
        </button>
      </div>

      {/* Discount Label */}
      {discount && (
        <div className="absolute top-2 left-2 bg-red-500 text-white text-xs font-semibold px-2 py-1 rounded">
          {discount}% OFF
        </div>
      )}

      {/* Product Image */}
      <CardHeader className="p-0">
        <img
          src={image}
          alt={name}
          className="h-48 w-full object-cover rounded-t"
        />
      </CardHeader>

      {/* Product Details */}
      <CardContent className="p-4">
        <CardTitle className="text-lg font-medium">{name}</CardTitle>
        <div className="mt-2">
          {/* Price with discount */}
          {discount ? (
            <div className="flex items-center gap-2">
              <span className="text-lg font-bold text-green-600">
                ${discountedPrice.toFixed(2)}
              </span>
              <span className="text-sm line-through text-gray-500">
                ${price.toFixed(2)}
              </span>
            </div>
          ) : (
            <span className="text-lg font-bold text-gray-900">
              ${price.toFixed(2)}
            </span>
          )}
        </div>
      </CardContent>

      {/* Add to Cart Button */}
      <CardFooter>
        <Button className="w-full bg-black text-white hover:bg-gray-800">
          Add to Cart
        </Button>
      </CardFooter>
    </Card>
  );
}

export default ProductCard;
