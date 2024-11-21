import "./App.css";
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";

function App() {
  return (
    <>
      <div className="flex items-center justify-center min-h-screen bg-background text-foreground">
        <Card className="w-[350px]">
          <CardHeader>
            <CardTitle>Create Project</CardTitle>
          </CardHeader>
          <CardContent>
            <Button variant="default">Default Button</Button>
          </CardContent>
          <CardFooter>
            <Button variant="outline">Cancel</Button>
            <Button>Deploy</Button>
          </CardFooter>
        </Card>
      </div>
    </>
  );
}

export default App;
