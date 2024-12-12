import ProductCard from '@/components/layout/product-card';
import Sidebar from '@/components/layout/sidebar';

function Home() {
  return (
    <div className="flex">
      <Sidebar />
      <div className="flex space-between p-4 flex-wrap">
        <ProductCard
          name="Wireless Headphones"
          price={150.0}
          image="https://picsum.photos/200"
          discount={20}
        />
        <ProductCard
          name="Smart Watch"
          price={200.0}
          image="https://picsum.photos/200"
        />
        <ProductCard
          name="Gaming Mouse"
          price={50.0}
          image="https://picsum.photos/200"
          discount={10}
        />
      </div>
    </div>
  );
}

export default Home;
