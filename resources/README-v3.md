# Easy-Animator

This is the latest version of this application.

## Instrcution

# To open files, please follow this format in command line:

-in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"

1. -in: input file has to be a text file.

2. -view: options include text”, “svg”, “visual”, "playback". "text" will output a text file to the pre-selected path or console if -out not specified. "svg" will output an SVG file to the pre-selected path and if path is not specified, a warning sign will pop up. "visual" will display an animation from input while "playback" provides the same visulization plus extra features: pause/resume/stop/repeat/play and stop music/adjust speed/show object IDs/show time/add new object/add operation for current objects/delete current objects.

3. -out: only SVG mode requires a pre-specified output path while -out cannoy be provided to"visual" and "SVG". -out is optional for "text", output will be in console if not provided.

4. -speed: if not provided, speed will be set to 1.

# If no arguments are provided, an empty "playback" view will be open, users can then either open a new file or add objects/motions to this empty canvas (size: 1000 x 1000).

# If arguments are not followed in this format, a waning sign will pop up.

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

## General design pattern

This application generally follows the Model-View-Controller design. To achieve the adapter design pattern so that controller does not need to constantly provide what the view needs, we seperate interface for model into two, one that only provides getter methods while the other one extending it plus other model methods. This way, view can own the reference to our model while it can only access the getter methods. To hide methods that users do not need, we also split up our view interface for interface segregation. With this design, in each mode of view, they are only exposed to methods they need. To close for future modification but extension, we encapsulate actions in view to callback methods, in a interface that controller implements. Whenever we need to add new actions, we can simply complete it in view without modifying our controller.

To enhance user-friendliness, we added two extra functions. One is the dashboard that show current time, so that a user can have more clues if that user wants to add an object or motion. The other function is that user can see object IDs if they check the "Show Obejct ID" box. This function can help users know which object they are deleting.

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

## Class detais

In model package, we have interfaces (Element, Feature) as well as classes that implemnts basic elements/objects functions(ElementImpl, Position, Size, ModelColor, Shape(Enum)). We also have other interfaces (Window, IModelView) and classes (WindowImpl, Image, Transfomation, LogNode, SvgHelper) that store and manipulate object information. Finally, the buildersrc folder contains classes that link between AnimatorReader and our model to process inputs.

In view package, we have several view interfaces due to interface segregation. IViewCommon provides a common method (set view-model, a model that only provides getter methods) across each different view. Each view also has their own interface (IViewPlayback for PlaybackView, IViewSVG for SvgView, IVIewVisual for VisualView and IViewText for TextView). The viewPanel package contains all the panels for VisualView and PlaybackView. VisualView only utilize AnimatorPanel to display animation while PlaybackView utilizes this panel plus extra ones. ControlBarPanel provides GUI for users to control animation. RealTimePanel and RealTimeSpeedPanel shows current time and speed in the animation. SettingPanel provides a GUI for users to add an object/motion or delete an Object.

In controller package, we have one interface for controllers in all different view mode to implement. PlaybackFeatures is an interface that helps encapsulate actions in view for callback methods. PlaybackFeatures is implemented by PlaybackController.

In util package, these are used to deal with input information including augument input (ArgsParser, EasyAnimatorSetter) and file input (AnimationBuilder(I), AnimationReader, AttributeSrcNode, Builder).

Finally, we have our EasyAnimatorRunner that is analogous to the start button for a machine. It instantiates models, views and controller accordingly and let the controller take over.

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

## Modification from last version

1. Last version does not have a controller and its job was taken up by our view. This time, we separate them for MVC design.

2. Last time there was only one interface for model, this time, we created a getter interface for the old model interface to extend. This is done for adapter design pattern.

3. We added a getter method for model: getElementIDAndShape. This enables our view to be able to get the current list of objects and their shape.

